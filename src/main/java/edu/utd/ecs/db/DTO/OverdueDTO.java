package edu.utd.ecs.db.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverdueDTO {
	
	private int loan_id;
	private String card_id;
	private int daysoverdue;
	private boolean isbookreturned;
}
