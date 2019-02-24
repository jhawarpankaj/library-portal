package edu.utd.ecs.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class Dummy {
	
	@Id
	private String uuid;
	private String isbn;
	private String title;
	private String author_name;
	
}
