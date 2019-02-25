package edu.utd.ecs.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Searchview {
	
	@Id
	private String isbn;
	private String title;
	private String author_name;
	private String isavailable;
	
}
