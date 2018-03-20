package edu.dlsu.securdeproject.security.brute_force_prevention;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
	@Autowired
	private LoginAttemptService loginAttemptService;

	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
		WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication.getDetails();

		loginAttemptService.loginSucceeded(auth.getRemoteAddress());
	}
}