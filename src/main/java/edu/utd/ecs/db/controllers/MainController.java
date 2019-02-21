package edu.utd.ecs.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.utd.ecs.db.model.Book;
import edu.utd.ecs.db.repository.BookRepository;

@Controller
@RequestMapping(path="/demo")
@EnableJpaRepositories("edu.utd.ecs.db.repository")
@EntityScan(basePackages = "edu.utd.ecs.db.model")
public class MainController {
	@Autowired
	private BookRepository bookRepository;
	
//	@GetMapping(path="/add") // Map ONLY GET Requests
//	public @ResponseBody String addNewUser (@RequestParam String name
//			, @RequestParam String email) {
//		// @ResponseBody means the returned String is the response, not a view name
//		// @RequestParam means it is a parameter from the GET or POST request
//
//		Book n = new Book();
//		n.setIsbn(name);
//		n.setTitle(email);
//		bookRepository.save(n);
//		return "Saved";
//	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Book> getAllBook() {
		// This returns a JSON or XML with the users
		return bookRepository.findAll();
	}

}
