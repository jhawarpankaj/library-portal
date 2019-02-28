package edu.utd.ecs.db.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckInDTO {
	
	private String cardno;
	private String isbn;
	private String ssn;
	private String borrower_name;
	private String date_in;
}
