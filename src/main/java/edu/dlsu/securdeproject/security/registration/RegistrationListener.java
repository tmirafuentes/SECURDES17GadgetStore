package edu.dlsu.securdeproject.security.registration;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationComepleteEvent> {
	@Autowired
	private MainService service;

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationComepleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationComepleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		service.createVerificationToken(user, token);

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