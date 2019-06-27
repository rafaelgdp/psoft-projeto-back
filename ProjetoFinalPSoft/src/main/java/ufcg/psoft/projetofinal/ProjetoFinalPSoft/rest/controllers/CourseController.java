package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.CourseDAO;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.CourseService;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.UserService;

import javax.servlet.ServletException;

import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCoursesBySubstring(@RequestBody String substring) throws ServletException {
    	List<Course> courses = courseService.findAllBySubstring(substring);
    	return courses;
    }

    @SuppressWarnings("deprecation")
	@PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) throws ServletException {

        if (course == null) {
            throw new ServletException("No valid course entered.");
        }
        
        URLDecoder decoder = new URLDecoder();
        course.setName(decoder.decode(course.getName()));
        Course bdCourse = courseService.findByName(course.getName());

        if (bdCourse != null) {
            throw new ServletException("Course with this name already registered.");
        }

        System.out.println("Saving this course: " + course.getName());

        return courseService.addCourse(course);

    }
    
    @DeleteMapping("/courses")
    public void deleteAllCourses() {
    	courseService.deleteAll();
    }
    
}
