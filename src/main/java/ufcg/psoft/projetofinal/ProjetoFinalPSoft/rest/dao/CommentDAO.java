package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Comment;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Course;


public interface CommentDAO<T, ID extends Serializable> extends JpaRepository<Comment, Integer> {
	
	@Query(value="select c from Comment c where c.commentCourse.id=:pcourseid")
	public List<Comment>findCommentByCourseId(@Param("pcourseid") Integer courseId);
	
	@SuppressWarnings("unchecked")
	public Comment save(Comment comment);
}
