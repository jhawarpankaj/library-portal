package edu.utd.ecs.db.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.utd.ecs.db.model.Fines;

@Repository
public interface FinesRepository extends JpaRepository<Fines, String>{
	
	@Query("select new edu.utd.ecs.db.model.Fines(u.loan_id, u.fine_amt, u.paid) from Fines u")
	List<Fines> getFinesDetails();
	
	@Query("select new edu.utd.ecs.db.model.Fines(u.loan_id, u.fine_amt, u.paid) from Fines u where u.loan_id=:loan_id")
	Fines getFinesTuple(@Param("loan_id") int loan_id);
}
