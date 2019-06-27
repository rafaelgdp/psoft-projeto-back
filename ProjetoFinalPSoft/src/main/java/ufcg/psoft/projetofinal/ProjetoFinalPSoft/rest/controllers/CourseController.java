package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.CourseAlreadyRegisteredException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.CourseNotFoundException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.NullCourseException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.CourseService;

import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/v1/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/find")
    public List<Course> getAllCoursesBySubstring(@RequestBody String substring) {
    	// For some reason, leading and ending " were arriving within the request body! :S
    	if (substring.startsWith("\""))
    		substring = substring.substring(1);
    	if (substring.endsWith("\""))
    		substring = substring.substring(0, substring.length()-1);
    	List<Course> courses = courseService.findAllBySubstring(substring);
    	return courses;
    }
    
    @GetMapping("/profile{id}")
    public Course getCourseById(@RequestParam(name = "id") Integer id) {
    	System.out.println("Looking for course with id " + id);
		Course course = courseService.findById(id);
		if (course == null) {
			System.out.println("Found nothing!");
			throw new CourseNotFoundException("Course not found!");
		}
		return course;
    }
    
    @GetMapping("")
    public List<Course> getAllCourses() {
    	return courseService.findAll();
    }

    @SuppressWarnings("deprecation")
    @PostMapping("")
    public Course addCourse(@RequestBody Course course) throws NullCourseException, CourseAlreadyRegisteredException {

    	System.out.println("Got...");
        if (course == null || course.getName().isEmpty()) {
            throw new NullCourseException("No valid course entered.");
        }
        
        URLDecoder decoder = new URLDecoder();
        course.setName(decoder.decode(course.getName()));
        Course bdCourse = courseService.findByName(course.getName());

        if (bdCourse != null) {
            throw new CourseAlreadyRegisteredException("Course with this name already registered.");
        }

        System.out.println("Saving this course: " + course.getName());

        return courseService.addCourse(course);

    }
    
    @DeleteMapping("")
    public void deleteAllCourses() {
    	courseService.deleteAll();
    }

}
