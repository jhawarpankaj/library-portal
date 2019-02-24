package edu.utd.ecs.db.DAO;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.utd.ecs.db.DTO.BookDTO;
import edu.utd.ecs.db.DTO.SearchDTO;
import edu.utd.ecs.db.model.Book;
import edu.utd.ecs.db.model.Dummy;

@Repository
public interface SearchRepositoryDummy extends JpaRepository<Dummy, String>{
	
//	@Query("select new edu.utd.ecs.db.DTO.SearchDTO(u.uuid, u.isbn, u.title, u.author_name) from Dummy as u where isbn like '%:searchWord%' OR title like '%:searchWord%' OR author_name like '%:searchWord%'")
//	List<SearchDTO> renderSearchResultsAsDTO(@Param("searchWord") String searchWord);
	
	@Query("select new edu.utd.ecs.db.DTO.SearchDTO(u.uuid, u.isbn, u.title, u.author_name) from Dummy as u where title like CONCAT('%', :searchWord, '%')")
	List<SearchDTO> renderSearchResultsAsDTO(@Param("searchWord") String searchWord);


}
