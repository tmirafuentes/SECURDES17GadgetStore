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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32)
			errors.rejectValue("username", "Size.userForm.username");
		if (mainService.findUserByUsername(user.getUsername()) != null)
			errors.rejectValue("username", "Duplicate.userForm.username");

		/* Validate Password */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
			errors.rejectValue("password", "Size.userForm.password");
		if (!user.getPasswordConfirm().equals(user.getPassword()))
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");

		/* Validate Email */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(user.getEmail());
		if (!matcher.matches())
			errors.rejectValue("email", "Duplicate.userForm.email");

		/* Validate First Name */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");

		/* Validate Last Name */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

		/* Validate Mail Address */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mailAddress", "NotEmpty");
		
		/* Validate Birthdate */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthdate", "NotEmpty");

		/* Validate Mobile Number */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		// Put Regex Somewhere
	}
}