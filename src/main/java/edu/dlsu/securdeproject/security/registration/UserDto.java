package edu.dlsu.securdeproject.security.registration;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.util.Calendar;

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

	@NotNull
	@NotEmpty
	private String password;
	private String passwordConfirm;

	@NotNull
	@NotEmpty
	private String email;

	private Calendar birthdate;

	private String mailAddress;

	private String mobileNumber;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getPasswordConfirm() { return passwordConfirm; }

	public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}	
}