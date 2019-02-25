package edu.utd.ecs.db.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidBorrowerDTO {
	
	private String cardId;
	private String ssn;

}
