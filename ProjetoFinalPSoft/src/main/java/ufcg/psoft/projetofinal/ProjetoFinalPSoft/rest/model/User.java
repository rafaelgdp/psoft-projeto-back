package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import lombok.Data;

import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Data
@Entity
public class User {

	@Id
    private String email;
	
	private String firstName;
    private String lastName;
    private String password;

    @ManyToMany(mappedBy="userLikes")
    private Set<Course> courses;

//  @OneToMany(mappedBy="grade")
//	@MapKey(name="email")
//	private Map<Course, Double> userGrades;

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

}
