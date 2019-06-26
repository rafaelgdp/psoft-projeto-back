package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.CourseAlreadyRegisteredException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.NullCourseException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.CourseDAO;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.CourseService;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.UserService;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCoursesBySubstring(@RequestBody String substring) {
    	List<Course> courses = courseService.findAllBySubstring(substring);
    	return courses;
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) throws NullCourseException, CourseAlreadyRegisteredException {

        if (course == null || course.getName().isEmpty()) {
            throw new NullCourseException("No valid course entered.");
        }

        Course bdCourse = courseService.findByName(course.getName());

        if (bdCourse != null) {
            throw new CourseAlreadyRegisteredException("Course with this name already registered.");
        }

        return courseService.addCourse(course);

    }

}
