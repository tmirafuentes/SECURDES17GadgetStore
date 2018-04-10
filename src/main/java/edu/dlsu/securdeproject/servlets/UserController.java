package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.security.SecurityService;
import edu.dlsu.securdeproject.security.registration.OnRegistrationCompleteEvent;
import edu.dlsu.securdeproject.classes.dtos.UserDto;
import edu.dlsu.securdeproject.security.registration.VerificationToken;
import edu.dlsu.securdeproject.services.TransactionService;
import edu.dlsu.securdeproject.services.UserService;
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
	private UserService userService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private SecurityService securityService;

	/*** Extra Stuff ***/
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
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
							   HttpServletRequest request, Model model)
	{
		/* Check if username already exists */
		if (userService.findUserByUsername(userForm.getUsername()) != null)
			bindingResult.rejectValue("username", "message.usernameDuplicate");

		/* Check if passwords match */
		if (!userForm.getPassword().equals(userForm.getPasswordConfirm()))
			bindingResult.rejectValue("passwordConfirm", "message.passwordConfirmNotMatch");

		/* Retry Registration if there are any errors */
		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "signup";
		}

		/* Register new user */
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(userService.findRoleByName("ROLE_USER"));
		User newUser = userService.saveNewUser(userForm, roles);

		/* Publish Event and send confirmation e-mail */
		try {
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newUser, request.getLocale(), userService.getAppUrl(request)));
		} catch (Exception me) {
			model.addAttribute("userForm", userForm);
			model.addAttribute("errorMessage", me);
			me.printStackTrace();
			return "signup";
		}

		/* Redirect to success page */
		return "redirect:/signup-confirm";
	}

	@RequestMapping(value = "/signup-confirm", method = RequestMethod.GET)
	public String signupConfirmPage(Model model)
	{
		return "signup-confirm";
	}

	/*** Confirm Registration ***/
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token)
	{
		Locale locale = request.getLocale();

		/* Check if Token is valid */
		VerificationToken verificationToken = securityService.getVerificationToken(token);
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
		userService.saveUser(user);
		//securityService.autologin(user.getUsername(), user.getPassword());
		return "redirect:/signup-success";
	}

	@RequestMapping(value = "/signup-success", method = RequestMethod.GET)
	public String signupSuccessPage(Model model)
	{
		return "signup-success";
	}

	/*** Resend Confirmation E-mail ***/
	@RequestMapping(value = "/signup-resend-email", method = RequestMethod.GET)
	public String resendVerificationEmail(HttpServletRequest request, @RequestParam("token") String existingToken)
	{
		/* Generate new token */
		VerificationToken newToken = securityService.generateNewVerificationToken(existingToken);

		/* Send email */
		User user = newToken.getUser();
		String appUrl = userService.getAppUrl(request);
		SimpleMailMessage email = userService.constructVerificationTokenEmail(appUrl, newToken.getToken(), user);
		securityService.sendEmail(email);

		return "redirect:/signup-success";
	}

	/*** Sign in ***/
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signInPage(Model model, String error, String logout) 
	{
		//if (error != null)
			//model.addAttribute("error", error);
			//model.addAttribute("error", messages.getMessage("message.badCredentials", null, null));

		if (logout != null)
			model.addAttribute("message", messages.getMessage("message.logoutSuccess", null, null));

		return "signin";
	}

	/*** Edit Account Details ***/
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String editAccountPage(Model model) 
	{
		/* Get current user profile */
		User currUser = userService.retrieveUser();

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
		userService.saveUser(userForm);
		return "redirect:/account";
	}

	/*** Forgot Password ***/
	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotPasswordPage(Model model) {
		return "forgot-password";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPasswordEmail(HttpServletRequest request, @RequestParam("email") String email, Model model)
	{
		System.out.println(request.get);
		/* Check if e-mail exists */
		User user = userService.findUserByEmail(email);
		if (user == null) {
			model.addAttribute("error", messages.getMessage("message.emailNotExist", null, null));
			return "forgot-password";
		}

		/* Create new Password Token */
		String token = UUID.randomUUID().toString();
		securityService.createPasswordResetToken(user, token);

		/* Send Password Reset Token E-mail */
		SimpleMailMessage passwordResetEmail = userService.constructResetTokenEmail(userService.getAppUrl(request), token, user);
		securityService.sendEmail(passwordResetEmail);

		return "redirect:/signup-confirm";
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public String changePasswordPage(Model model, @RequestParam("id") Long id, @RequestParam("token") String token) 
	{
		/* Validate Password Reset Token */
		String result = securityService.validatePasswordResetToken(id, token);
		if (result != null) {
			model.addAttribute("message", messages.getMessage("message." + result, null, null));
			return "redirect:/login";
		}

		return "change-password";
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public String changePasswordSubmit(@RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm) 
	{
		/* Validate the passwords */
		if (!validationService.validatePassword(password, passwordConfirm))
			return "change-password";

		/* Save New Password */
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userService.saveNewPassword(user, password);

		return "redirect:/signup-success";
	}

	/*** Create New Admin (Pwedeng Waley) ***/
	@RequestMapping(value = "/admin/signup", method = RequestMethod.GET)
    public String adminSignupPage(Model model)
    {
	    model.addAttribute("adminForm", new UserDto());
	    return "admin-signup";
    }

    @RequestMapping(value = "/admin/signup", method = RequestMethod.POST)
    public String adminSignupSubmit(@ModelAttribute("adminForm") @Valid UserDto adminForm, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adminForm", adminForm);
            return "admin-signup";
        }

        /* Save new Admin to the database */
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(userService.findRoleByName("ROLE_USER"));
        roles.add(userService.findRoleByName("ROLE_ADMIN"));
        userService.saveNewUser(adminForm, roles);

        return "redirect:/admin";
    }

    /*** View Transactions For Overriding ***/
    @RequestMapping(value = "/admin/trans", method = RequestMethod.GET)
    public String adminTransactionsPage(Model model) {
        model.addAttribute("transactions", transactionService.findAllTransactions());
        return "admin-trans";
    }

    /*** View Users ***/
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String adminUsersPage(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin-users";
    }
}