package edu.dlsu.securdeproject.security.brute_force_prevention;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Autowired
	private MessageSource messages;

	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
										final AuthenticationException exception) throws IOException, ServletException 
	{
		setDefaultFailureUrl(".signin/?error=true");

		super.onAuthenticationFailure(request, response, exception);


		String errorMessage = messages.getMessage("message.badCredentials", null, locale);
		if (exception.getMessage().equalsIgnoreCase("blocked"))
			errorMessage = messages.getMessage("auth.message.blocked", null, locale);
	}
}