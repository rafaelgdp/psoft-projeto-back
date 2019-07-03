package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "course_user_likes",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();

    @OneToMany(mappedBy = "commentCourse")
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
        return likes;
    }

    public void setUserLikes(Set<User> userLikes) {
        this.likes = userLikes;
    }

    public void addUserLike(User user) {
        if (user != null && !likes.contains(user))
            likes.add(user);
    }

    public void removeUserLike(User user) {
    	if (user != null) {
    		for (User u : this.getUserLikes()) {
        		if (u.getEmail().equals(user.getEmail())) {
        			this.likes.remove(u);
        			break;
        		}
        	}	
    	}
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        if (comment != null) {
        	if (comments == null) {
        		comments = new ArrayList<>();
        	}
            comments.add(comment);
        }
    }
    
    public String toString() {
    	String r = getId() + " - " + getName() + " - " + likes.toString() + " - ";
    	for (Comment c : comments) {
    		r += c.getCommentAuthor().getEmail() + ": " + c.getMessage();
    	}
    	r += " #" + comments.size();
    	return r;
    }

	public boolean checkIfUserLiked(String email) {
		for (User u : getUserLikes()) {
			if (u.getEmail().equals(email))
				return true;
		}
		return false;
	}
    
}
