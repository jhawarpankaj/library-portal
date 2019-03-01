package edu.utd.ecs.db.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.utd.ecs.db.DTO.CheckInDTO;
import edu.utd.ecs.db.DTO.SearchDTO;
import edu.utd.ecs.db.model.Checkinview;
import edu.utd.ecs.db.model.Searchview;

@Repository
public interface CheckInRepository extends JpaRepository<Checkinview, String>{
	
	@Query("select new edu.utd.ecs.db.DTO.CheckInDTO(u.cardno, u.isbn, u.ssn, u.borrower_name, u.date_in) from Checkinview as u where (LOWER(u.cardno) =:searchWord OR LOWER(u.isbn) =:searchWord OR LOWER(u.borrower_name) like LOWER(CONCAT('%', :searchWord, '%'))) AND u.date_in is null")
	List<CheckInDTO> renderSearchResultsAsDTO(@Param("searchWord") String searchWord);
	
}
