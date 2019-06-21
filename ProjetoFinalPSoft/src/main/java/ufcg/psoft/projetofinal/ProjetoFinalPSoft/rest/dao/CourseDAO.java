package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;

// Here, we may add queries to the JpaRepo
public interface CourseDAO<T, ID extends Serializable> extends JpaRepository<Course, Integer> {
	
	@Query(value="select c from Course c where c.name like %:pname%")
	public List<Course> findAllBySubstring(@Param("pname") String substr);

	@Query(value="select c from Course c where c.name=:pname")
	public Course findByName(@Param("pname") String courseName);

	@SuppressWarnings("unchecked")
	public Course save(Course course);

}
