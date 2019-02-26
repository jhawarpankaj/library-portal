package edu.utd.ecs.db.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.utd.ecs.db.DAO.BorrowerRepository;
import edu.utd.ecs.db.DAO.SearchRepository;
import edu.utd.ecs.db.DTO.StatusAndMessageDTO;
import edu.utd.ecs.db.model.Borrower;

@RestController
@RequestMapping("/api/borrower")
@EnableJpaRepositories("edu.utd.ecs.db.DAO")
public class CreateBorrowerController {
	
	@Autowired
	private SearchRepository searchRepository;
	
	@Autowired
	private BorrowerRepository borrowerRepository;

	@RequestMapping("/create/{values}")
	@ResponseBody
    public StatusAndMessageDTO bookSearch(@PathVariable("values") String values) {
		String cleanString = values.trim();
		System.out.println(cleanString);
		String[] formFields = cleanString.split("\\|\\|");
		System.out.println(formFields[0]);
		String name = formFields[0].trim();
		System.out.println(name);
		String ssn = formFields[1].trim();
		System.out.println(ssn);
		String address = formFields[2].trim();
		System.out.println(address);
		String phone = formFields[3].trim();
		System.out.println(phone);
		long maxIDFromBookLoans = borrowerRepository.getMaxIDFromBorrower() + 1;
		String stringRepMaxID = String.valueOf(maxIDFromBookLoans);
		String cardId = ("000000" + stringRepMaxID).substring(stringRepMaxID.length());
		
		if(name == null || ssn == null || address == null || phone == null) {
			return new StatusAndMessageDTO(false, "Null fields are not allowed.");
		}
		else if(name.isEmpty() || ssn.isEmpty() || address.isEmpty() || phone.isEmpty() ) {
			return new StatusAndMessageDTO(false, "Empty fields are not allowed.");
		}
		else if(ssn.length()<9 || ssn.length()>11){
			return new StatusAndMessageDTO(false, "Incorrect SSN format.");
		}
		else if(phone.length()<10 || phone.length()>14){
			return new StatusAndMessageDTO(false, "Incorrect phone format.");
		}
		else if(borrowerRepository.checkIfSSNExists(ssn)==1) {
			System.out.println(borrowerRepository.checkIfSSNExists(ssn) + ssn);
			return new StatusAndMessageDTO(false, "Borrower already exists.");
		}
		else {			
			Borrower borrower = new Borrower(cardId, ssn, name, address, phone, maxIDFromBookLoans+1);
			System.out.println(borrower);
			borrowerRepository.save(borrower);
			return new StatusAndMessageDTO(true, "Borrower added successfully.");
		}		
    }
}
