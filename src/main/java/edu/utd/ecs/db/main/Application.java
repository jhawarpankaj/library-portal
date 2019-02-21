package edu.utd.ecs.db.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"edu.utd.ecs.db.controllers", 
		"edu.utd.ecs.db.model", "edu.utd.ecs.db.repository"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
