package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;

public interface CourseDAO<T, ID extends Serializable> extends JpaRepository<Course, Integer> {
	
	public Course findById();

	@Query(value="SELECT * FROM Course WHERE name LIKE '%:pname%'")
	public List<Course> findAllBySubstring(@Param("pname") String substr);
	
}
