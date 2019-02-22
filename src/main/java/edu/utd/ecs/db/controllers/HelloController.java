package edu.utd.ecs.db.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Map<String, Object> model) {
        return "search";
    }
}
