package edu.dlsu.securdeproject.security.registration;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.security.SecurityService;
import edu.dlsu.securdeproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;

	private MessageSource messages;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		securityService.createEmailVerificationToken(user, token);

		SimpleMailMessage confirmRegisterEmail = userService.constructVerificationTokenEmail(event.getAppUrl(), token, user);
		securityService.sendEmail(confirmRegisterEmail);
	}
}