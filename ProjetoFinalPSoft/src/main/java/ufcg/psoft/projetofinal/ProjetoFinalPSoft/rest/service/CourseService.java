package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course.CourseNotFoundException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.CourseDAO;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;

import java.util.List;

@Service
public class CourseService {

    private final CourseDAO<User, String> CourseDAO;

    CourseService(CourseDAO<User, String> CourseDAO) {
        this.CourseDAO = CourseDAO;
    }

    public User create(User user) {
    	throw new NotYetImplementedException("Not implemented yet!");
    }

    public User update(Course courseToUpdate) throws CourseNotFoundException {
    	throw new NotYetImplementedException("Not implemented yet!");
    }

    public List<Course> findAll() {
        return CourseDAO.findAll();
    }
    
    public List<Course> findAllBySubstring(String substr) {
        return CourseDAO.findAllBySubstring(substr);
    }
    
    public Course findById(Integer id) {
        return CourseDAO.findById(id).get();
    }

}
