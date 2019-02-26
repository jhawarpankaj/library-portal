package edu.utd.ecs.db.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.utd.ecs.db.DAO.CheckInRepository;
import edu.utd.ecs.db.DAO.TotalLoansRepository;
import edu.utd.ecs.db.DTO.CheckInDTO;
import edu.utd.ecs.db.DTO.StatusAndMessageDTO;
import edu.utd.ecs.db.model.Book_Loans;
import edu.utd.ecs.db.service.Book_LoansService;

@RestController
@RequestMapping("/api/book/checkin")
@EnableJpaRepositories("edu.utd.ecs.db.DAO")
public class CheckInBooksController {
	
	@Autowired
	private CheckInRepository CheckInRepository;
	
	@Autowired
	private TotalLoansRepository totalLoansRepository;
	
	@Autowired
	private Book_LoansService book_LoansService;
	
//	@Autowired
//	private Book_LoansRepository book_LoansRepository;

	@RequestMapping("/search/{values}")
	@ResponseBody
    public List<ArrayList<CheckInDTO>> bookSearch(@PathVariable("values") String values) {
		String cleanString = values.trim();
		Set<String> set = new HashSet<String>();
		List<ArrayList<CheckInDTO>> totalResults = new ArrayList<ArrayList<CheckInDTO>>();
		for(String word: cleanString.split(" ")) {
			String temp = word.trim();
			if(!set.contains(temp.toLowerCase())) {
				set.add(temp.toLowerCase());
			}			
		}
		for(String word : set) {
			List<CheckInDTO> temp = CheckInRepository.renderSearchResultsAsDTO(word);
			System.out.println(temp);
			totalResults.add((ArrayList<CheckInDTO>) temp);
		}
		return totalResults;
    }
		
	@RequestMapping("/check/{values}")
	@ResponseBody
	public StatusAndMessageDTO bookCheckIn(@PathVariable("values") String values) {
		
		System.out.println("Values: " + values);
		String cleanString = values.trim();
		String cardId = cleanString.split(" ")[0];
		String isbn = cleanString.split(" ")[1];
		System.out.println("CardId: " + cardId + ",ISBN:  " + isbn);
		Book_Loans book_loan = totalLoansRepository.getBookLoansTuple(isbn, cardId);
		System.out.println("I am here:" + book_loan.getCard_id());
//		Optional<Book_Loans> findById = book_LoansRepository.findById(11);
				
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
//		book_LoansService.remove(book_loan);
		book_loan.setDate_in(dateFormat.format(currentDate));
//		book_LoansService.insert(book_loan);
		totalLoansRepository.save(book_loan);
		return new StatusAndMessageDTO(true, "Book checked in successfully.");
    }
}
