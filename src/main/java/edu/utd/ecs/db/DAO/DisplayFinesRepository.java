package edu.utd.ecs.db.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.utd.ecs.db.DTO.SearchDTO;
import edu.utd.ecs.db.model.Displayfines;
import edu.utd.ecs.db.model.Searchview;

@Repository
public interface DisplayFinesRepository extends JpaRepository<Displayfines, String>{
	
	@Query("select new edu.utd.ecs.db.model.Displayfines(u.loan_ids, u.card_id, u.total_fine) from Displayfines u")
	List<Displayfines> getFineDetails();
	
	@Query("select new edu.utd.ecs.db.model.Displayfines(u.loan_ids, u.card_id, u.total_fine) from Displayfines u where u.card_id=:card_id")
	List<Displayfines> getPayFineDetails(@Param("card_id") String card_id);
	
}
