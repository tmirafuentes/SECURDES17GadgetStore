package edu.dlsu.securdeproject.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Customer {
	private long userId;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String mailAddress;
	private Date birthdate;
	private String mobileNumber;
	private Set<Transaction> transactions;

	public Customer() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getUserId() { return userId; }

	public void setUserId(long userId) { this.userId = userId; }

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
		this.mobileNumber = mobileNumber;
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
}