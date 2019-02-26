package edu.utd.ecs.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Checkinview {
	
	@Id
	private String cardno;
	private String isbn;
	private String ssn;
	private String borrower_name;
	private String date_in;
	
}
