package edu.dlsu.securdeproject.security.password_recovery;

import edu.dlsu.securdeproject.classes.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PasswordResetToken {
	private static final int EXPIRATION = 60 * 24;

	private Long id;
	private String token;
	private User user;
	private Date expiryDate;

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