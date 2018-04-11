package edu.dlsu.securdeproject.classes.dtos;

import edu.dlsu.securdeproject.validation.PasswordMatches;
import edu.dlsu.securdeproject.security.escapeInput.InputEscaper;
import edu.dlsu.securdeproject.validation.ValidEmail;
import edu.dlsu.securdeproject.validation.ValidPassword;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.util.Calendar;
import java.util.Date;

@PasswordMatches
public class UserDto {
	@NotNull
	@NotEmpty
	private String firstName;

	@NotNull
	@NotEmpty
	private String lastName;

	@NotNull
	@NotEmpty
	private String username;

	@ValidPassword
	@NotNull
	@NotEmpty
	private String password;
	private String passwordConfirm;

	@ValidEmail
	@NotNull
	@NotEmpty
	private String email;

	private Date birthdate;

	@NotNull
	@NotEmpty
	private String mailAddress;

	@NotNull
	@NotEmpty
	private String mobileNumber;

	private InputEscaper Iescape;
	public UserDto() {
		Iescape = new InputEscaper();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = Iescape.inputToBeEscaped(username);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Iescape.inputToBeEscaped(password);
	}

	@Transient
	public String getPasswordConfirm() { return passwordConfirm; }

	public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = Iescape.inputToBeEscaped(passwordConfirm); }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		this.email = Iescape.inputToBeEscaped(email);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = Iescape.inputToBeEscaped(firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = Iescape.inputToBeEscaped(lastName);
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = Iescape.inputToBeEscaped(mailAddress);
	}

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = Iescape.inputToBeEscaped(mobileNumber);
	}	
}