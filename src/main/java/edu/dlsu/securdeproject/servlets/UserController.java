package edu.dlsu.securdeproject.servlets;

@Controller
public class UserController {
	/*** Services ***/
	@Autowired
	private MainService mainService;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private SecurityService securityService;

	/*** Extra Stuff ***/
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	private MessageSource messages;

	/***
	 ***
		 URL MAPPING
	 ***
	 ***/

	/*** Register Account ***/
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpPage(Model model) 
	{
		/* Initialize user registration form */
		model.addAttribute("userForm", new UserDto());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUpSubmit(@ModelAttribute("userForm") @Valid UserDto userForm, BindingResult bindingResult,
							   WebRequest request, Model model)
	{
		/* Check if username already exists */
		if (mainService.findUserByUsername(userForm.getUsername()) != null)
			bindingResult.rejectValue("username", "message.usernameDuplicate");

		/* Retry Registration if there are any errors */
		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "signup";
		}

		/* Register new user */
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(mainService.findRoleByName("ROLE_USER"));
		User newUser = mainService.saveUser(userForm, roles);

		/* Publish Event and send confirmation e-mail */
		try {
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newUser, request.getLocale(), request.getContextPath()));
		} catch (Exception me) {
			model.addAttribute("userForm", userForm);
			model.addAttribute("errorMessage", messages.getMessage("message.emailSendError", null, request.getLocale()));
			return "signup";
		}

		/* Redirect to success page */
		return "redirect:/signup-success";
	}

	/*** Confirm Registration ***/
	@RequestMapping(value = "/signup-confirm", method = RequestMethod.GET)
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) 
	{
		Locale locale = request.getLocale();

		/* Check if Token is valid */
		VerificationToken verificationToken = mainService.getVerificationToken(token);
		if (verificationToken == null) {
			model.addAttribute("errorMessage", messages.getMessage("message.invalidToken", null, locale));
			return "redirect:/error";
		}

		/* Check if token has expired */
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("errorMessage", messages.getMessage("message.expiredToken", null, locale));
			model.addAttribute("expired", true);
			model.addAttribute("token", token);
			return "redirect:/error";
		}

		/* Enable user account and auto login */
		user.setEnabled(true);
		mainService.saveUser(user);
		securityService.autologin(user.getUsername(), user.getPassword());
		return "redirect:/";
	}

	/*** Resend Confirmation E-mail ***/
	@RequestMapping(value = "/signup-resend-email", method = RequestMethod.GET)
	public String resendVerificationEmail(HttpServletRequest request, @RequestParam("token") String existingToken)
	{
		/* Generate new token */
		VerificationToken newToken = mainService.generateNewVerificationToken(existingToken);

		/* Send email */
		User user = newToken.getUser();

	}

	/*** Sign in ***/
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signInPage(Model model, String error, String logout) {

	}


	/***
	 ***
	 	 OTHER FUNCTIONS
	 ***
	 ***/

	/*** Template URL ***/
	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	/*** Construct Template Email ***/
	private SimpleMailMessage constructEmail(String subject, String message, User user) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setMessage(message);
		email.setTo(user.getEmail());
		return email;
	}

	/*** Construct Specific Emails ***/
	private SimpleMailMessage constructVerificationTokenEmail(String contextPath, VerificationToken newToken, User user) {
		String confirmationUrl = contextPath + "/signup-confirm?token=" + newToken.getToken();
		String message = "Confirm your registration by clicking here: ";
		return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
	}
}