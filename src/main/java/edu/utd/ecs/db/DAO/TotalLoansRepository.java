package edu.utd.ecs.db.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.utd.ecs.db.DTO.TotalLoansDTO;
import edu.utd.ecs.db.DTO.MaxIDLoanDTO;
import edu.utd.ecs.db.DTO.SearchDTO;
import edu.utd.ecs.db.model.Book_Loans;
import edu.utd.ecs.db.model.Searchview;

@Repository
public interface TotalLoansRepository extends JpaRepository<Book_Loans, String>{
		
	@Query("select new edu.utd.ecs.db.DTO.TotalLoansDTO(count(u.card_id)) from Book_Loans u where u.card_id = :cardId")
	TotalLoansDTO totalLoans(@Param("cardId") String cardId);
	
	@Query("select max(id) from Book_Loans")
	MaxIDLoanDTO getMaxIDFromBookLoans();

}
