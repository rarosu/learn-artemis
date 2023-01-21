package learn.artemis.bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import learn.artemis.bff.dto.SubmissionDTO;
import learn.artemis.bff.dto.SubmissionResponseDTO;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SubmissionService {
	private final JmsTemplate jmsTemplate;

	public SubmissionService(@Autowired final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public SubmissionResponseDTO submit(SubmissionDTO dto) throws JMSException {
		AtomicReference<Message> message = new AtomicReference<>();
		jmsTemplate.convertAndSend("learn.artemis.queue", dto, m -> {
			message.set(m);
			return m;
		});

		return new SubmissionResponseDTO(message.get().getJMSMessageID());
	}
}