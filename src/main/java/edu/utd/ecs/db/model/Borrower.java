package edu.utd.ecs.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Borrower {
	@Id
	private String cardid;	
	private String ssn;
	private String bname;
	private String address;
	private String phone;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public Borrower() {		
	}
}
