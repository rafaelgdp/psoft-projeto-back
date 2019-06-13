package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.UserService;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("/v1/auth")
public class CourseController {

    @Autowired
    private UserService userService;

    @GetMapping("/subjects")
    public List<Course> authenticate(@RequestBody String substring) throws ServletException {

        // Recupera o usuario
        List<Course> authUser = userService.findByEmail(user.getEmail());

        System.out.println("I tried to find this login on the database: " + user.getEmail());

        // verificacoes
        if(authUser == null) {
            throw new ServletException("Usuario nao encontrado!");
        }

        if(!authUser.getPassword().equals(user.getPassword())) {
            throw new ServletException("Senha invalida!");
        }

        String token = Jwts.builder().
                setSubject(authUser.getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .compact();

        return new LoginResponse(token);

    }
}
