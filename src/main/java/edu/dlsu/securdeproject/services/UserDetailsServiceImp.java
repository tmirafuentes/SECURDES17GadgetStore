package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.security.brute_force_prevention.LoginAttemptService;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    /* External Services */
    @Autowired
    private UserService userService;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    private MessageSource messages;

    /* Functions */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip))
            throw new RuntimeException("blocked");

        /* Try retrieving a user */
        User user = userService.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Wrong username or password");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles())
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(),
                                                                      true, true, true, grantedAuthorities);
    }

    protected String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null)
            return request.getRemoteAddr();

        return xfHeader.split(",")[0];
    }
}
