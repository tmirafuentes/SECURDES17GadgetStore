package edu.dlsu.securdeproject.security.registration;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	@Autowired
	private MainService service;

	@Autowired
	private MessageSource messages;

	@Autowired
    private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		service.createEmailVerificationToken(user, token);

		String recipientAddress = user.getEmail();
		String subject = "Confirm Registration at Troy's Toys";
		String confirmationUrl = event.getAppUrl() + "/signup-confirm?token=" + token;
		String message = messages.getMessage("message.registerSuccess", null, event.getLocale());

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message + " http://localhost:8080" + confirmationUrl);
		mailSender.send(email);
	}
}