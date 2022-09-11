package learn.artemis.bff.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import learn.artemis.bff.dto.SubmissionDTO;
import learn.artemis.bff.dto.SubmissionResponseDTO;

@RestController
public class SubmissionController {
	@PostMapping
	public SubmissionResponseDTO submit(@RequestBody SubmissionDTO message) {
		return new SubmissionResponseDTO("10");
	}
}