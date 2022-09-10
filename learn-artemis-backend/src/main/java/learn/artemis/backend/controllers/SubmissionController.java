package learn.artemis.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubmissionController {
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}