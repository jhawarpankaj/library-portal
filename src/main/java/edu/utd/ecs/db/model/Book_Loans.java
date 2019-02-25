package edu.utd.ecs.db.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Book_Loans {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loan_id;
	private String isbn;
	private String card_id;
	private String date_out;
	private String due_date;
	private String date_in;
	
	public Book_Loans(String isbn, String card_id, String date_out, String due_date, String date_in) {
		this.isbn = isbn;
		this.card_id = card_id;
		this.date_out = date_out;
		this.date_in = date_in;
		this.due_date = due_date;
		this.date_in = date_in;
	}
}
