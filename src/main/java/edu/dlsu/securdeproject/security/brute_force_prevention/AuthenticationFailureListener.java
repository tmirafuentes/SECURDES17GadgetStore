package edu.dlsu.securdeproject.security.brute_force_prevention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LoginAttemptService loginAttemptService;

	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			loginAttemptService.loginFailed(request.getRemoteAddr());
		} else {
			loginAttemptService.loginFailed(xfHeader.split(",")[0]);
		}
	}
}