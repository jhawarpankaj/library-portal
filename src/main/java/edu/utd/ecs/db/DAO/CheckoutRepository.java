//package edu.utd.ecs.db.DAO;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
////import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import edu.utd.ecs.db.DTO.SearchDTO;
//import edu.utd.ecs.db.DTO.ValidBorrowerDTO;
//import edu.utd.ecs.db.model.Book_Loans;
//import edu.utd.ecs.db.model.Borrower;
//import edu.utd.ecs.db.model.Searchview;
//
//@Repository
//public interface CheckoutRepository extends JpaRepository<Book_Loans, String>{
//		
//	@Query(value = "insert into commit_activity_link (commit_id, activity_id) VALUES (?1, ?2)", nativeQuery = true)
//	@Query("insert into Book_Loans (loan_id, isbn, card_id, date_out, due_date, date_in) VALUES (:
//	void insertLoan(@Param("cardId") String cardId);
//
//}
