package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "course")
    private Set<User> likes = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<Comment> comments = new ArrayList<>();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Set<User> getUserLikes() {
        return likes;
    }

    public void setUserLikes(Set<User> userLikes) {
        this.likes = userLikes;
    }

    public void addUserLike(User user) {
        if (user != null)
            likes.add(user);
    }

    public void removeUserLike(User user) {
        likes.remove(user);
    }

//	public Map<String, Double> getUserGrades() {
//		return userGrades;
//	}
//
//	public void setUserGrades(Map<User, Double> userGrades) {
//		this.userGrades = userGrades;
//	}
//	
//	public Double getGradeMean() {
//		Double total = 0.0;
//		for (Double grade : userGrades.values()) {
//			total += grade;
//		}
//		return total / userGrades.size();
//	}

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
