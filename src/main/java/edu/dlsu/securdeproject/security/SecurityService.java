package edu.dlsu.securdeproject.security;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.repositories.PasswordTokenRepository;
import edu.dlsu.securdeproject.repositories.VerificationTokenRepository;
import edu.dlsu.securdeproject.security.password_recovery.PasswordResetToken;
import edu.dlsu.securdeproject.security.registration.VerificationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

@Service
public class SecurityService implements SecurityServiceInterface {
	/* Repositories */
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private PasswordTokenRepository passwordTokenRepository;

	/* Mail */
	public JavaMailSender mailSender;

	/* Authentication Stuff */
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails)
			return ((UserDetails)userDetails).getUsername();

		return null;
	}

	@Override
	public void autologin(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
			= new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	public boolean authenticateAccount(String username, String password) {
		UsernamePasswordAuthenticationToken uapt = new UsernamePasswordAuthenticationToken(username, password);
		authenticationManager.authenticate(uapt);

		if (uapt.isAuthenticated() && findLoggedInUsername().equals(username))
			return true;

		return false;
	}

	/* Forgotten Password Token Creation */
	public void createPasswordResetToken(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(user, token);
		passwordTokenRepository.save(myToken);
	}

	/* Retrieve Password Token */
	public PasswordResetToken getPasswordToken(String token) {
		return passwordTokenRepository.findByToken(token);
	}

	/* Send Forgot Password E-mail Token */
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}

	/* Validate Password Token */
	public String validatePasswordResetToken(Long id, String token) {
		/* Invalid Token */
		PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
		if ((passToken == null) || (passToken.getUser().getUserId() != id))
			return "invalidToken";

		/* Expired Token */
		Calendar cal = Calendar.getInstance();
		if((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0)
			return "expiredToken";

		User user = passToken.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}

	/* E-mail Verification Token Creation */
	public void createEmailVerificationToken(User user, String token) {
		VerificationToken newToken = new VerificationToken(user, token);
		verificationTokenRepository.save(newToken);
	}

	/* Update E-Mail Verification Token */
	public VerificationToken generateNewVerificationToken(String existingToken) {
		VerificationToken vToken = getVerificationToken(existingToken);
		vToken.setToken(UUID.randomUUID().toString());
		vToken = verificationTokenRepository.save(vToken);
		return vToken;
	}

	/* Retrieve E-mail Verification Token */
	public VerificationToken getVerificationToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}

	/* Encrypt Password */
	public String encryptPassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
}