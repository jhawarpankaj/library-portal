package edu.utd.ecs.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class Borrower {
	@Id
	private String cardid;	
	private String ssn;
	private String bname;
	private String address;
	private String phone;
}
