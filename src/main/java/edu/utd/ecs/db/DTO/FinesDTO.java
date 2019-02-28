package edu.utd.ecs.db.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FinesDTO {
	
	private int loan_id;
	private double fine_amt;
	private boolean paid;
}
