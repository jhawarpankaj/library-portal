package edu.utd.ecs.db.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
	
	private String isbn;
	private String title;
	
}
