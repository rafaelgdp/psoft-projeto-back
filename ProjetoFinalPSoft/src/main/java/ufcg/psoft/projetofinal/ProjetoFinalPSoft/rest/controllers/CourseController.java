package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.comment.NullCommentException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.CourseAlreadyRegisteredException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.CourseNotFoundException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.NullCourseException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.NullUserException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Comment;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.CommentService;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.CourseService;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service.UserService;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

@RestController
@RequestMapping("/v1/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private UserService userService;

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
    
    @GetMapping("/profile{courseid}")
    public Course getCourseById(@RequestParam(name = "courseid") Integer id) {
    	System.out.println("Looking for course with id " + id);
		Course course = courseService.findById(id);
		if (course == null) {
			System.out.println("Found nothing!");
			throw new CourseNotFoundException("Course not found!");
		}
		for (Iterator<User> i = course.getUserLikes().iterator(); i.hasNext();) {
			cleanUserPassword(i.next());
		}
		return course;
    }
    
    private void cleanUserPassword(User user) {
    	user.setPassword("x");
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
        
        course.setName(URLDecoder.decode(course.getName()));
        Course bdCourse = courseService.findByName(course.getName());

        if (bdCourse != null) {
            throw new CourseAlreadyRegisteredException("Course with this name already registered.");
        }

        System.out.println("Saving this course: " + course.getName());

        return courseService.addCourse(course);

    }
    
    @PostMapping("/comment{courseid}")
    public String addCommentToCourse(@RequestParam(name = "courseid") Integer id, @RequestBody Comment comment) {
    	
    	System.out.println("Got " + id + " " + comment.getMessage());
    	
    	if (comment == null || comment.getMessage() == null) {
    		throw new NullCommentException("Comment is null");
    	}
    	
    	if (comment.getCommentAuthor() == null) {
    		throw new NullUserException("Comment's author is null");
    	}
    	
    	Course course = courseService.findById(id);
    	
    	if (course == null) { 
    		throw new CourseNotFoundException("Course not found on database");
    	}
    	
    	courseService.addComment(id, comment);
    	
    	return comment.getMessage();
    }
    
    @DeleteMapping("/comment{commentid}")
    public String deleteCommentFromCourse(@RequestParam(name = "commentid") Integer commentId) {

    	if (commentId == null) {
			throw new NullCommentException("Null comment ID!"); 
    	}
    	
    	Comment comment = commentService.findCommentById(commentId);

    	commentService.delete(comment);
    	
    	return comment.getMessage();
    }
    
    @GetMapping("/comments{courseid}")
    public List<Comment> getCourseComments(@RequestParam(name = "courseid") Integer courseid) {
    	
    	if (courseid == null) {
    		throw new NullCourseException("Course ID null!");
    	}
    	
    	Course course = courseService.findById(courseid);
    	
    	if (course == null) {
    		throw new CourseNotFoundException("Course with desired ID not found!");
    	}
    	
    	ArrayList<Comment> comments = new ArrayList<>();
    	List<Comment> foundComments = commentService.findCommentsByCourseId(course.getId());
    	for (Comment c : foundComments) {
    		c.getCommentAuthor().setPassword("x"); // This is not to leak user's password.
    		if (c.getCommentCourse().getId() == course.getId()) {
    			comments.add(c);
    		}
    	}
    	
    	return comments;
    		
    }
    
    @DeleteMapping("")
    public void deleteAllCourses() {
    	courseService.deleteAll();
    }

}
