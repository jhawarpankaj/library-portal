package edu.utd.ecs.db.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.utd.ecs.db.DAO.SearchRepository;
import edu.utd.ecs.db.DAO.SearchRepositoryDummy;
import edu.utd.ecs.db.DTO.BookDTO;
import edu.utd.ecs.db.DTO.SearchDTO;
import edu.utd.ecs.db.model.Book;

@RestController
@RequestMapping("/api/book/")
@EnableJpaRepositories("edu.utd.ecs.db.DAO")
public class SearchBooksController {
	
	@Autowired
	private SearchRepositoryDummy searchRepository;

	@RequestMapping("/search/{values}")
	@ResponseBody
    public List<SearchDTO> bookSearch(@PathVariable("values") String values) {
		String cleanString = values.trim();
//		for(String word : cleanString.split(" ")) {
//			
//		}
        List<SearchDTO> renderSearchResultsAsDTO = searchRepository.renderSearchResultsAsDTO(cleanString);
		return renderSearchResultsAsDTO;
    }
}
