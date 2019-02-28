package edu.utd.ecs.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Overdue {
	
	@Id
	private int loan_id;
	private String card_id;
	private int daysoverdue;
	private boolean isbookreturned;
	
}
