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

@Repository
public interface SearchRepository extends JpaRepository<Book, String>{
	
	@Query("SELECT title FROM Book t where t.isbn = :isbn") 
	List<Book> findBookByISBN(@Param ("isbn") String isbn);
	
	@Query("SELECT title FROM Book t where t.isbn = :isbn") 
	List<Book> findById2(@Param ("isbn") String isbn);
	
//	@Query("SELECT new edu.utd.ecs.db.DTO.BookDTO(u.isbn, u.title) FROM Book u WHERE u.title = :title")
//    List<BookDTO> retrieveBookNameAsDTO(@Param("title") String title);
//	
//	@Query("SELECT new edu.utd.ecs.db.DTO.SearchDTO(t1.name AS AUTHOR_NAME, t2.isbn AS ISBN, t2.title AS TITLE) FROM authors t1 JOIN SELECT b1.isbn AS isbn, b1.title AS title, AUTHOR_ID FROM book b1 JOIN book_authors b2 ON b1.ISBN = b2.isbn WHERE LOWER(b1.isbn) LIKE LOWER('%104%')) AS t2 ON t1.AUTHOR_ID = t2.author_id") 
//	List<SearchDTO> renderSearchResultsAsDTO2(@Param("searchWord") String searchWord);

//	@Query(value = "(SELECT new edu.utd.ecs.db.DTO.SearchDTO(2, 2, 3) UNION ", nativeQuery = true)
//	List<SearchDTO> renderSearchResultsAsDTO(@Param("searchWord") String searchWord);
	
//	@Query("select new edu.utd.ecs.db.DTO.SearchDTO(u.ISBN, u.TITLE, u.AUTHOR_NAME) from dummy as u where ISBN like '%:searchWord%' OR TITLE like '%:searchWord%' OR AUTHOR_NAME like '%:searchWord%'")
//	List<SearchDTO> renderSearchResultsAsDTO(@Param("searchWord") String searchWord);
	
//	@Query(value = "(SELECT new edu.utd.ecs.db.DTO.SearchDTO(t1.author_name, t1.isbn, title) FROM book INNER JOIN (SELECT name AS author_name, isbn FROM authors INNER JOIN book_authors ON authors.author_id = book_authors.author_id WHERE LOWER(name) LIKE LOWER('%:searchWord%')) AS t1 ON book.ISBN = t1.isbn) UNION (SELECT name AS AUTHOR_NAME, t2.isbn AS ISBN, title AS TITLE FROM authors t1 JOIN (SELECT b1.isbn AS isbn, title, author_id FROM book b1 JOIN book_authors b2 ON b1.ISBN = b2.ISBN WHERE LOWER(title) LIKE LOWER('%:searchWord%')) AS t2 ON t1.AUTHOR_ID = t2.author_id) UNION (SELECT t1.name AS AUTHOR_NAME, t2.isbn AS ISBN, t2.title AS TITLE FROM authors t1 JOIN (SELECT b1.isbn AS isbn, b1.title AS title, AUTHOR_ID FROM book b1 JOIN book_authors b2 ON b1.ISBN = b2.isbn WHERE LOWER(b1.isbn) LIKE LOWER('%:searchWord%')) AS t2 ON t1.AUTHOR_ID = t2.author_id);", nativeQuery = true)
//	List<SearchDTO> renderSearchResultsAsDTO(@Param("searchWord") String searchWord);

}
