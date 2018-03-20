package edu.dlsu.securdeproject.security.brute_force_prevention;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	private LoginAttemptService loginAttemptService;

	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
		WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication.getDetails();

		loginAttemptService.loginFailed(auth.getRemoteAddress());
	}
}