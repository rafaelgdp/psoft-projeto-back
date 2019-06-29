package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course commentCourse; // This relates to course

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User commentAuthor; // This relates to User
	
	private Date date;

	private String message;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Course getCommentCourse() {
		return commentCourse;
	}

	public void setCommentCourse(Course commentCourse) {
		this.commentCourse = commentCourse;
	}

	public User getCommentAuthor() {
		return commentAuthor;
	}

	public void setCommentAuthor(User commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
