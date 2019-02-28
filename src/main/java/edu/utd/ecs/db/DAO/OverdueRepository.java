package edu.utd.ecs.db.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import edu.utd.ecs.db.model.Overdue;

@Repository
public interface OverdueRepository extends JpaRepository<Overdue, Integer>{
	
	@Query("select new edu.utd.ecs.db.model.Overdue(u.loan_id, u.card_id, u.daysoverdue, u.isbookreturned) from Overdue u")
	List<Overdue> getOverdueDetails();
}
