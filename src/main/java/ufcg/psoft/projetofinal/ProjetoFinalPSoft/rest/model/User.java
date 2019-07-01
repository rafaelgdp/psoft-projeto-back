package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="email")
@Table(name = "`user`")
public class User {

	@Id
    private String email;
	
	private String firstName;
    private String lastName;
    private String password;

    @ManyToMany(mappedBy = "likes")
    @JsonBackReference
    private Set<Course> likedCourses = new HashSet<>();
    
    @OneToMany(mappedBy = "commentAuthor")
    private List<Comment> comments = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String nome) {
		this.firstName = nome;
	}

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

	public Set<Course> getLikedCourses() {
		return likedCourses;
	}

	public void setLikedCourses(Set<Course> likedCourses) {
		this.likedCourses = likedCourses;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
    

}
