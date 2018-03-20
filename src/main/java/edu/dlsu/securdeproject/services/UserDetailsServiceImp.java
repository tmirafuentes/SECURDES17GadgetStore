package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    /* External Services */
    @Autowired
    private MainService mainService;

    @Autowired
    private LoginAttemptService loginAttemptService; 

    @Autowired
    private HttpServletRequest request;

    /* Functions */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip))
            throw new RuntimeException("blocked");

        /* Try retrieving a user */
        try {

            User user = mainService.findUserByUsername(username);

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role : user.getRoles())
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

            if (user == null)
                return new org.springframework.security.core.userdetails.User(" ", " ", 
                                getAuthorities(Arrays.asList(mainService.findRoleByName("ROLE_USER"))));

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword().grantedAuthorities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null)
            return request.getRemoteAddr();

        return xfHeader.split(",")[0];
    }
}
