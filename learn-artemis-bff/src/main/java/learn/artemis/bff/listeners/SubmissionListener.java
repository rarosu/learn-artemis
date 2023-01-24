package learn.artemis.bff.listeners;

import learn.artemis.bff.dto.SubmissionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class SubmissionListener {
	Logger logger = LoggerFactory.getLogger(SubmissionListener.class);

	@JmsListener(destination = "learn.artemis.queue")
	public void onMessage(Message<SubmissionDTO> message) {
		SubmissionDTO dto = message.getPayload();
		logger.info("Received a message with ID " + message.getHeaders().getId() + ": " + dto.getMessageName() + " " + dto.getValue());
	}
}