package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Course {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
