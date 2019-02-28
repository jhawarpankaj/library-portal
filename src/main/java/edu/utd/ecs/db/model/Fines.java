package edu.utd.ecs.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Fines {
	
	@Id
	private int loan_id;
	private double fine_amt;
	private boolean paid;
	
	public Fines() {
		
	}
}
