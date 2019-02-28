package edu.utd.ecs.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Displayfines {
	
	@Id
	private String loan_ids;
	private String card_id;
	private double total_fine;
	
}
