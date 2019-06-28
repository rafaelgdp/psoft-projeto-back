package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service;

import org.springframework.stereotype.Service;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.comment.NullCommentException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.CourseNotFoundException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.UserNotFoundException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.CourseDAO;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Comment;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    private final CourseDAO<User, String> courseDAO;
    private final UserService userService;
    private final CommentService commentService;

    CourseService(CourseDAO<User, String> CourseDAO, UserService userService, CommentService commentService) {
        this.courseDAO = CourseDAO;
        this.userService = userService;
        this.commentService = commentService;
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
    
    public Comment addComment(Integer courseId, Comment comment) {
    	
   	   	if (comment == null || comment.getCommentAuthor() == null) {
    		throw new NullCommentException("Null comment!");
    	}
    	
   	   	User user = userService.findByEmail(comment.getCommentAuthor().getEmail());
   	   	
    	if (user == null) {
    		throw new UserNotFoundException("Comment user not found!");
    	}
    	
    	Course course = courseDAO.findById(courseId).get();
    	
    	if (course == null) {
    		throw new CourseNotFoundException("Course to which comment was supposed to be added to not found!");
    	}
    	
    	comment.setDate(new Date());
    	comment.setCommentCourse(course);
    	commentService.create(comment);
    	return comment;
    }
    
    public void deleteAll() {
    	courseDAO.deleteAll();
    }

}
