package edu.utd.ecs.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.utd.ecs.db.model.Book_Loans;

public interface Book_LoansRepository extends JpaRepository<Book_Loans, Integer> {
}
