package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Comment;


public interface CommentDAO<T, ID extends Serializable> extends JpaRepository<Comment, Integer> {
}
