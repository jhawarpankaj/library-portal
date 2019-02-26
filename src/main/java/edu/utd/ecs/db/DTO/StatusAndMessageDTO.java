package edu.utd.ecs.db.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusAndMessageDTO {
	
	private boolean status;
	private String message;

}
