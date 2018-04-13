package edu.dlsu.securdeproject.security.registration;

import edu.dlsu.securdeproject.classes.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken {
	private static final int EXPIRATION = 60 * 24;

	private Long id;
	private String token;
	private User user;
	private Date expiryDate;

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public VerificationToken() {}

	public VerificationToken(final User user, final String token) {
		this.user = user;
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
} 