package edu.dlsu.securdeproject.security.brute_force_prevention;

import edu.dlsu.securdeproject.security.audit_trail.AuthLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Autowired
	private MessageSource messages;
	@Autowired
	private AuthLogService authLogService;

	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
										final AuthenticationException exception) throws IOException, ServletException
	{
		authLogService.createNewLog((String)request.getSession().getAttribute("username"), false, request.getRemoteAddr());

		if (exception.getMessage().equalsIgnoreCase("blocked"))
			setDefaultFailureUrl("/index");
		else
			setDefaultFailureUrl("/signin?error=true");
		super.onAuthenticationFailure(request, response, exception);

		String errorMessage = messages.getMessage("message.badCredentials", null, null);
		if (exception.getMessage().equalsIgnoreCase("blocked"))
			errorMessage = messages.getMessage("message.bruteForceBlock", null, null);

		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}
}