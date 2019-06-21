package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer> {

}
