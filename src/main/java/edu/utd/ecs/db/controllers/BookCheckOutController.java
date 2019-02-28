package edu.utd.ecs.db.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.utd.ecs.db.DAO.TotalLoansRepository;
import edu.utd.ecs.db.DAO.BorrowerRepository;
import edu.utd.ecs.db.DTO.StatusAndMessageDTO;
import edu.utd.ecs.db.DTO.TotalLoansDTO;
import edu.utd.ecs.db.DTO.ValidBorrowerDTO;
import edu.utd.ecs.db.model.Book_Loans;
import edu.utd.ecs.db.service.Book_LoansService;

@RestController
@RequestMapping("/api/checkout")
@ComponentScan("edu.utd.ecs.db.service")
@EnableJpaRepositories("edu.utd.ecs.db.DAO")
public class BookCheckOutController {
		
	@Autowired
	private BorrowerRepository validBorrowerRepository;
	
	@Autowired
	private TotalLoansRepository totalLoansRepository;	
	
	@Autowired
	private Book_LoansService book_LoansService;

	@RequestMapping("/{values}")
	@ResponseBody
    public StatusAndMessageDTO checkout(@PathVariable("values") String values) {
		String cleanString = values.trim();
		String cardId = cleanString.split(" ")[0];
		String isbn = cleanString.split(" ")[1];
		
		ValidBorrowerDTO validBorrower = validBorrowerRepository.isValidBorrower(cardId);
		if(validBorrower==null) {
			return new StatusAndMessageDTO(false, "Not a valid CardId.");
		}
		TotalLoansDTO totalLoans = totalLoansRepository.totalLoansForCardID(cardId);
		System.out.println(totalLoans.getTotalLoans());
		if(totalLoans.getTotalLoans()>=3) {
			return new StatusAndMessageDTO(false, "User has already borrowed 3 books");
		}
		else if(totalLoansRepository.checkIfBookAlreadyCheckedOut(isbn) == 1) {
			return new StatusAndMessageDTO(false, "This book has already been checked out. Please refresh!");
		}
		else {
//			MaxIDLoanDTO maxIDFromBookLoans = totalLoansRepository.getMaxIDFromBookLoans();
//			int maxLoanID = maxIDFromBookLoans.getMaxID();
			
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			Date currentDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.DATE, 14);
			Date currentDatePlusFourteen = c.getTime();
			
	//		int l = String.valueOf(maxLoanID).length();
	
			Book_Loans bookLoan = new Book_Loans(isbn, cardId, dateFormat.format(currentDate), dateFormat.format(currentDatePlusFourteen), null);
			int insert = book_LoansService.insert(bookLoan);
			System.out.println("Inserted record with loanId:" + insert);
			return new StatusAndMessageDTO(true, "Book checked out successfully.");
		}
    }
}
