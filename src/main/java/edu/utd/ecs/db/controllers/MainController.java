package edu.utd.ecs.db.controllers;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableJpaRepositories("edu.utd.ecs.db.repository")
@EntityScan(basePackages = "edu.utd.ecs.db.model")
public class MainController {

	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search";
    }
	
	@RequestMapping(value = "/checkin", method = RequestMethod.GET)
    public String checkin() {
        return "checkin";
    }
	
	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
    public String borrower() {
        return "borrower";
    }
	
	@RequestMapping(value = "/fines", method = RequestMethod.GET)
    public String fines() {
        return "fines";
    }
}
