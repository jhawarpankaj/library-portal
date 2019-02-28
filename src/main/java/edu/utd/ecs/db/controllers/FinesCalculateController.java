package edu.utd.ecs.db.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.utd.ecs.db.DAO.DisplayFinesRepository;
import edu.utd.ecs.db.DAO.FinesRepository;
import edu.utd.ecs.db.DAO.OverdueRepository;
import edu.utd.ecs.db.DTO.StatusAndMessageDTO;
import edu.utd.ecs.db.model.Displayfines;
import edu.utd.ecs.db.model.Fines;
import edu.utd.ecs.db.model.Overdue;

@RestController
@RequestMapping("/api/fines")
@EnableJpaRepositories("edu.utd.ecs.db.DAO")
public class FinesCalculateController {
	
	@Autowired
	private OverdueRepository overdueRepository;
	
	@Autowired
	private FinesRepository finesRepository;
	
	@Autowired
	private DisplayFinesRepository displayFinesRepository;
	
	final static double perDayFine = 0.25;

	@RequestMapping("/calculate")
	@ResponseBody
    public StatusAndMessageDTO calculateFines() {
		List<Overdue> overdueDetails = overdueRepository.getOverdueDetails();
		Map<Integer, Double> fineMap = new HashMap<Integer, Double>();
		for(Overdue overdueObj : overdueDetails) {
			Integer loan_id = overdueObj.getLoan_id();
			int daysoverdue = overdueObj.getDaysoverdue();
			double fines = daysoverdue * perDayFine;
			fineMap.put(loan_id, fines);
		}
		List<Fines> finesDetails = finesRepository.getFinesDetails();
		Set<Integer> fineSet = new HashSet<Integer>();
		Map<String, Double> notPaidMap = new HashMap<String, Double>();
		for(Fines finesObj: finesDetails) {
			int loanId = finesObj.getLoan_id();
			boolean isPaid = finesObj.isPaid();
			fineSet.add(loanId);
			if(!isPaid) {
				double fineInFineTable = finesObj.getFine_amt();
				double calculatedFine = fineMap.get(loanId);
				if(fineInFineTable != calculatedFine) {
					Fines fineTuple = finesRepository.getFinesTuple(loanId);
					fineTuple.setFine_amt(calculatedFine);
					finesRepository.save(fineTuple);					
				}
				fineSet.add(loanId);
			}
		}
		for(Map.Entry<Integer, Double> entry : fineMap.entrySet()) {			
			int loanId = entry.getKey();
			Double fine = entry.getValue();
			if(!fineSet.contains(loanId)) {
				Fines fineTuple = new Fines(loanId, fine, false);
				finesRepository.save(fineTuple);
			}
		}
		return new StatusAndMessageDTO(true, "Updated all fines.");	
    }
	
	@RequestMapping("/display")
	@ResponseBody
	public List<Displayfines> displayFines() {
		return displayFinesRepository.getFineDetails();
	}
	
	@RequestMapping("/pay/{values}")
	@ResponseBody
	public StatusAndMessageDTO payFines(@PathVariable("values") String values) {
		String cleanString = values.trim();
		List<Displayfines> payFineDetails = displayFinesRepository.getPayFineDetails(cleanString);
		for(Displayfines updateFineObj: payFineDetails) {
			String loan_ids = updateFineObj.getLoan_ids();
			String[] loan_id = loan_ids.split(",");
			for(String loan: loan_id) {
				Fines finesTuple = finesRepository.getFinesTuple(Integer.parseInt(loan));
				finesTuple.setPaid(true);
				finesRepository.save(finesTuple);
			}
			
		}
		return new StatusAndMessageDTO(true, "Fine paid.");
	}
}
