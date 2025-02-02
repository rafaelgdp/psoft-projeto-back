package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.UserNotFoundException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.UserWithEmailAlreadyRegistered;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.WrongEmailOrPasswordException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "*")
public class UserController {

    private final String TOKEN_KEY = "banana";

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public LoginResponse retrieveUserDetails(@RequestBody User user) throws ServletException {

    	// verificacoes
        if(user == null) {
            throw new UserNotFoundException("Usuario nulo!");
        }
    	
        // Recupera o usuario
        User authUser = userService.findByEmail(user.getEmail());
        
        System.out.println("I tried to find this login on the database: " + user.getEmail());

        // verificacoes
        if(authUser == null) {
            throw new UserNotFoundException("Usuario nao encontrado!");
        }

        if(!authUser.getPassword().equals(user.getPassword())) {
            throw new WrongEmailOrPasswordException("Senha invalida!");
        }

        String token = Jwts.builder().
                setSubject(authUser.getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).
                compact();

        return new LoginResponse(token, new UserResponse(authUser.getFirstName(), authUser.getLastName(), authUser.getEmail()));

    }
    
    private class UserResponse {
    	public String firstName;
    	public String lastName;
    	public String email;
    	
    	public UserResponse(String f, String l, String e) {
    		firstName = f;
    		lastName = l;
    		email = e;
    	}
    }
    
    private class LoginResponse {
        public String token;
        public UserResponse user;

        public LoginResponse(String token, UserResponse user) {
            this.token = token;
            this.user = user;
        }
    }
    
    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<User> register(@RequestBody User user) throws ServletException {
		
        if (user == null) {
            throw new ServletException("Null user!!");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            throw new UserWithEmailAlreadyRegistered(user.getEmail());
        }

        User newUser = userService.create(user);
        System.out.println("Salvei este user no bd: " + newUser.getEmail());
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

    }
    
}
