package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;

public interface CourseDAO<T, ID extends Serializable> extends JpaRepository<Course, Integer> {
	
	public Course findById();
}
