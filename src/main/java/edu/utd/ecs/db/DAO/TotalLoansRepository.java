package edu.utd.ecs.db.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.utd.ecs.db.DTO.TotalLoansDTO;
import edu.utd.ecs.db.model.Book_Loans;

@Repository
public interface TotalLoansRepository extends JpaRepository<Book_Loans, String>{
		
	@Query("select new edu.utd.ecs.db.DTO.TotalLoansDTO(count(u.card_id)) from Book_Loans u where u.card_id = :cardId and u.date_in is null")
	TotalLoansDTO totalLoansForCardID(@Param("cardId") String cardId);
	
	@Query("select count(*) from Book_Loans where isbn=:isbn and date_in is null")
	int checkIfBookAlreadyCheckedOut(@Param("isbn") String isbn);
	
	@Query("select new edu.utd.ecs.db.model.Book_Loans(u.loan_id, u.isbn, u.card_id, u.date_out, u.due_date, u.date_in) from Book_Loans u where isbn=:isbn and card_id=:cardId and date_in is null")
	Book_Loans getBookLoansTuple(@Param("isbn") String isbn, @Param("cardId") String cardId);

}