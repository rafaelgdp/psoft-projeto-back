package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Set<String> userLikes;
	private Map<String, Double> userGrades;
	private List<Comment> comments;
	
	private String name;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getUserLikes() {
		return userLikes;
	}

	public void setUserLikes(Set<String> userLikes) {
		this.userLikes = userLikes;
	}
	
	public void addUserLike(String email) {
		if (email != null)
			userLikes.add(email);
	}
	
	public void removeUserLike(String email) {
		userLikes.remove(email);
	}

	public Map<String, Double> getUserGrades() {
		return userGrades;
	}

	public void setUserGrades(Map<String, Double> userGrades) {
		this.userGrades = userGrades;
	}
	
	public Double getGradeMean() {
		Double total = 0.0;
		for (Double grade : userGrades.values()) {
			total += grade;
		}
		return total / userGrades.size();
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		if (comment != null) {
			comments.add(comment);	
		}
	}
}
