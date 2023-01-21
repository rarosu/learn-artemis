package learn.artemis.bff.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import learn.artemis.bff.dto.SubmissionDTO;
import learn.artemis.bff.dto.SubmissionResponseDTO;
import learn.artemis.bff.services.SubmissionService;

import javax.jms.JMSException;

@RestController
public class SubmissionController {
	private final SubmissionService submissionService;

	public SubmissionController(SubmissionService submissionService) {
		this.submissionService = submissionService;
	}

	@PostMapping
	public SubmissionResponseDTO submit(@RequestBody SubmissionDTO dto) throws JMSException {
		return submissionService.submit(dto);
	}
}