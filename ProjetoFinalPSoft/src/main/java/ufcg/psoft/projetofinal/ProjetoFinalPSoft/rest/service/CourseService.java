package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service;

import org.springframework.stereotype.Service;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.CourseDAO;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Comment;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

@Service
public class CourseService {

    private final CourseDAO<User, String> courseDAO;
    private final UserService userService;

    CourseService(CourseDAO<User, String> CourseDAO, UserService us) {
        this.courseDAO = CourseDAO;
        this.userService = us;
    }

    public List<Course> findAll() {
        return courseDAO.findAll();
    }
    
    public List<Course> findAllBySubstring(String substr) {
        return courseDAO.findAllBySubstring(substr);
    }
    
    public Course findById(Integer id) {
        return courseDAO.findById(id).get();
    }

    public Course findByName(String name) {
        return courseDAO.findByName(name);
    }

    public Course addCourse(Course course) {
        return courseDAO.save(course);
    }
    
    public Comment addCommentToCourse(Integer courseId, Comment comment) throws ServletException {
    	
    	if (userService.findByEmail(comment.getEmail()) == null) {
    		throw new ServletException("Comment user not found!");
    	}
    	
    	Course course = courseDAO.findById(courseId).get();
    	
    	if (course == null) {
    		throw new ServletException("Course to which comment was supposed to be added to not found!");
    	}
    	
    	course.addComment(comment);
    	courseDAO.save(course);
    	
    	return comment;
    }
    
    public void deleteAll() {
    	courseDAO.deleteAll();
    }

}
