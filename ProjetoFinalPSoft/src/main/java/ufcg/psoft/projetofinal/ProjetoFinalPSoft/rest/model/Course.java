package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "USER_LIKES",
            joinColumns = {@JoinColumn(name = "courseId")},
            inverseJoinColumns = {@JoinColumn(name = "userEmail")})
    private Set<User> userLikes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "id")
    private List<Comment> comments;

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
        return userLikes;
    }

    public void setUserLikes(Set<User> userLikes) {
        this.userLikes = userLikes;
    }

    public void addUserLike(User user) {
        if (user != null)
            userLikes.add(user);
    }

    public void removeUserLike(User user) {
        userLikes.remove(user);
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
