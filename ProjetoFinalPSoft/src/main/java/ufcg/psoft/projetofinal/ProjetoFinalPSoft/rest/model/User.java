package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

	@Id
    private String email;
	
	private String firstName;
    private String lastName;
    private String password;

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
