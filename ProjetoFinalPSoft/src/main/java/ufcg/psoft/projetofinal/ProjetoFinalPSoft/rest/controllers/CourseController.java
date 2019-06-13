package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.CourseDAO;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.UserService;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> authenticate(@RequestBody String substring) throws ServletException {

        
    }
}
