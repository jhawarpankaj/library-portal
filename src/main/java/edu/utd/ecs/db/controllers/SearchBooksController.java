package edu.utd.ecs.db.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.utd.ecs.db.DAO.SearchRepository;
import edu.utd.ecs.db.DAO.SearchRepository;
import edu.utd.ecs.db.DTO.BookDTO;
import edu.utd.ecs.db.DTO.SearchDTO;
import edu.utd.ecs.db.model.Book;

@RestController
@RequestMapping("/api/book")
@EnableJpaRepositories("edu.utd.ecs.db.DAO")
public class SearchBooksController {
	
	@Autowired
	private SearchRepository searchRepository;

	@RequestMapping("/search/{values}")
	@ResponseBody
    public List<ArrayList<SearchDTO>> bookSearch(@PathVariable("values") String values) {
		String cleanString = values.trim();
		Set<String> set = new HashSet<String>();
		List<ArrayList<SearchDTO>> totalResults = new ArrayList<ArrayList<SearchDTO>>();
		for(String word: cleanString.split(" ")) {
			String temp = word.trim();
			if(!set.contains(temp.toLowerCase())) {
				set.add(temp.toLowerCase());
			}			
		}		
		for(String word : set) {
			List<SearchDTO> temp = searchRepository.renderSearchResultsAsDTO(word);
			System.out.println(temp);
			totalResults.add((ArrayList<SearchDTO>) temp);
		}
		return totalResults;
    }
}
