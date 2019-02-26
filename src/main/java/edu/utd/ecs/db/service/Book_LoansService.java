package edu.utd.ecs.db.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.utd.ecs.db.model.Book_Loans;

@Repository
@Transactional
public class Book_LoansService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public int insert(Book_Loans book_loans) {
		entityManager.persist(book_loans);
		return book_loans.getLoan_id();
	}
}
