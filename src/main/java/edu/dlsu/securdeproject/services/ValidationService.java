package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.security.registration.UserDto;
import edu.dlsu.securdeproject.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationService implements Validator {
	/* Local Variables */
	@Autowired
	private MainService mainService;

	/* Email Validator */
	private Pattern pattern;
	private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)* (.[A-Za-z]{2,})$";

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserDto user = (UserDto) o;

		/* Validate Username */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "message.emptyForm");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32)
			errors.rejectValue("username", "message.emptyForm");
		if (mainService.findUserByUsername(user.getUsername()) != null)
			errors.rejectValue("username", "message.usernameDuplicate");

		/* Validate Password */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.emptyForm");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
			errors.rejectValue("password", "message.passwordNotMet");
		if (!user.getPasswordConfirm().equals(user.getPassword()))
			errors.rejectValue("passwordConfirm", "message.passwordConfirmNotMatch");

		/* Validate Email */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "message.emptyForm");
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(user.getEmail());
		//if (!matcher.matches())
			//errors.rejectValue("email", "message.emailInvalid");

		/* Validate First Name */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "message.emptyForm");

		/* Validate Last Name */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "message.emptyForm");

		/* Validate Mail Address */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mailAddress", "message.emptyForm");
		
		/* Validate Birthdate */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthdate", "message.emptyForm");

		/* Validate Mobile Number */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "message.emptyForm");
		// Put Regex Somewhere
	}

	public boolean validatePassword(String password, String passwordConfirm) {
		if (password.equals(passwordConfirm))
			return true;
		return false;
	}
}