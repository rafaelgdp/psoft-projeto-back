package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service;

import org.springframework.stereotype.Service;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.CommentDAO;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Comment;

@Service
public class CommentService {
	
	private final CommentDAO<Comment, Integer> commentDAO;
	
	public CommentService(CommentDAO cdao) {
		this.commentDAO = cdao;
	}
	
	public Comment findCommentById(Integer id) {
		return commentDAO.findById(id).get();
	}
	
	public Comment create(Comment comment) {
		return commentDAO.save(comment);
	}
}
