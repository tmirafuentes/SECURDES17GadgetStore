package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidationService implements Validator {
	@Autowired
	private MainService mainService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		/* Validate Username */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32)
			errors.rejectValue("username", "Size.userForm.username");
		if (mainService.findUserByUsername(user.getUsername()) != null)
			errors.rejectValue("username", "Duplicate.userForm.username");

		/* Validate Password */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getUsername().length() < 8 || user.getUsername().length() > 32)
			errors.rejectValue("username", "Size.userForm.password");
		if (!user.getPasswordConfirm().equals(user.getPassword()))
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");

		/* Validate Email */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		// Put Regex Somewhere

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