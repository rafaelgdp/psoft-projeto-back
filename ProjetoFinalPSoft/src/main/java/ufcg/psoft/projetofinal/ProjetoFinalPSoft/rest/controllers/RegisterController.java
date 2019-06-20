package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.UserWithEmailAlreadyRegistered;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.UserService;

import javax.servlet.ServletException;

@RestController
@RequestMapping("/v1/auth")
public class RegisterController {

    private ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.UserService userService;

    RegisterController(UserService userService) {
        this.setUserService(userService);
    }
    
    private void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<User> register(@RequestBody User user) throws ServletException {

		System.out.println("GOOT");
		
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
