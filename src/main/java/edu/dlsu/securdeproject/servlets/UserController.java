package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.security.SecurityService;
import edu.dlsu.securdeproject.security.registration.OnRegistrationCompleteEvent;
import edu.dlsu.securdeproject.classes.dtos.UserDto;
import edu.dlsu.securdeproject.security.registration.VerificationToken;
import edu.dlsu.securdeproject.services.MainService;
import edu.dlsu.securdeproject.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

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
		String appUrl = getAppUrl(request);
		SimpleMailMessage email = constructVerificationTokenEmail(appUrl, newToken.getToken(), user);
		mainService.sendEmail(email);

		return "redirect:/signup-success";
	}

	/*** Sign in ***/
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signInPage(Model model, String error, String logout) 
	{
		if (error != null)
			model.addAttribute("error", messages.getMessage("message.badCredentials", null, null));

		if (logout != null)
			model.addAttribute("message", messages.getMessage("message.logoutSuccess", null, null));

		return "signin";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signInSubmit(Model model, @RequestParam("username") String username, @RequestParam("password") String password)
	{
		/* Log-in */
		securityService.autologin(username, password);

		/* Redirect to Admin home page if Admin */
		User currUser = mainService.findUserByUsername(securityService.findLoggedInUsername());
		for(Role role: currUser.getRoles())
			if (role.getName().equals("ROLE_ADMIN"))
				return "redirect:/admin";

		return "redirect:/welcome";
	}

	/*** Edit Account Details ***/
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String editAccountPage(Model model) 
	{
		/* Get current user profile */
		User currUser = retrieveUser();

		model.addAttribute("userForm", currUser);
		return "account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String editAccountSubmit(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) 
	{
		/* If error, redirect */
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", messages.getMessage("message.accountUpdate", null, null));
			model.addAttribute("userForm", userForm);
			return "account";
		}

		/* Save changes */
		mainService.saveUser(userForm);
		return "redirect:/account";
	}

	/*** Forgot Password ***/
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPasswordEmail(HttpServletRequest request, @RequestParam("email") String email, Model model)
	{
		/* Check if e-mail exists */
		User user = mainService.findUserByEmail(email);
		if (user == null) {
			model.addAttribute("error", messages.getMessage("message.emailNotExist", null, null));
			return "forgot-password";
		}

		/* Create new Password Token */
		String token = UUID.randomUUID().toString();
		mainService.createPasswordResetToken(user, token);

		/* Send Password Reset Token E-mail */
		SimpleMailMessage passwordResetEmail = constructResetTokenEmail(getAppUrl(request), token, user);
		mainService.sendEmail(passwordResetEmail);

		return "redirect:/forgot-password-confirm";
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public String changePasswordPage(Model model, @RequestParam("id") Long id, @RequestParam("token") String token) 
	{
		/* Validate Password Reset Token */
		String result = mainService.validatePasswordResetToken(id, token);
		if (result != null) {
			model.addAttribute("message", messages.getMessage("message." + result, null, null));
			return "redirect:/login";
		}

		return "/change-password";
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public String changePasswordSubmit(@RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm) 
	{
		/* Validate the passwords */
		if (!validationService.validatePassword(password, passwordConfirm))
			return "/change-password";

		/* Save New Password */
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mainService.saveNewPassword(user, password);

		return "redirect:/change-password-success";
	}

	/*** View All Transactions ***/
	@RequestMapping(value = "/purchases", method = RequestMethod.GET)
	public String viewTransactionsPage(Model model) 
	{
		/* Get Current User */
		User currUser = retrieveUser();

		/* Get All Transactions from User */
		ArrayList<Transaction> allTransactions = mainService.findTransactionsByUser(currUser);
		model.addAttribute("purchases", allTransactions);

		return "transactions";
	}

	/*** Create New Admin (Pwedeng Waley) ***/
	@RequestMapping(value = "/admin-signup", method = RequestMethod.GET)
    public String adminSignupPage(Model model)
    {
	    model.addAttribute("adminForm", new UserDto());
	    return "admin-signup";
    }

    @RequestMapping(value = "/admin-signup", method = RequestMethod.POST)
    public String adminSignupSubmit(@ModelAttribute("adminForm") @Valid UserDto adminForm, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adminForm", adminForm);
            return "admin-signup";
        }

        /* Save new Admin to the database */
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(mainService.findRoleByName("ROLE_USER"));
        roles.add(mainService.findRoleByName("ROLE_ADMIN"));
        mainService.saveUser(adminForm, roles);

        return "redirect:/admin";
    }

    /*** View Transactions For Overriding ***/
    @RequestMapping(value = "/admin/trans", method = RequestMethod.GET)
    public String adminTransactionsPage(Model model) {
        model.addAttribute("transactions", mainService.findAllTransactions());
        return "admin-trans";
    }

    /*** View Users ***/
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String adminUsersPage(Model model) {
        model.addAttribute("users", mainService.findAllUsers());
        return "admin-users";
    }

	/***
	 ***
	 	 OTHER FUNCTIONS
	 ***
	 ***/

	/*** Retrieve current user ***/
	private User retrieveUser() 
	{
		String username = securityService.findLoggedInUsername();
		return mainService.findUserByUsername(username);
	} 	 

	/*** Template URL ***/
	private String getAppUrl(HttpServletRequest request) 
	{
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	/*** Construct Template Email ***/
	private SimpleMailMessage constructEmail(String subject, String message, User user) 
	{
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(message);
		email.setTo(user.getEmail());
		return email;
	}

	/*** Construct Specific Emails ***/
	private SimpleMailMessage constructVerificationTokenEmail(String contextPath, String newToken, User user)
	{
		String confirmationUrl = contextPath + "/signup-confirm?token=" + newToken;
		String message = messages.getMessage("message.registerSuccess", null, null);
		return constructEmail("Resend Troy's Toys Confirmation E-mail", message + " \r\n" + confirmationUrl, user);
	}

	private SimpleMailMessage constructResetTokenEmail(String contextPath, String newToken, User user)
	{
		String confirmationUrl = contextPath + "/change-password?id=" + user.getUserId() + 
								 "&token=" + newToken;
		String message = messages.getMessage("message.resetPassword", null, null);
		return constructEmail("Reset Password For Troy's Toys", message + " \r\n" + confirmationUrl, user);
	}
}