package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.classes.dtos.UserDto;
import edu.dlsu.securdeproject.repositories.RoleRepository;
import edu.dlsu.securdeproject.repositories.UserRepository;
import edu.dlsu.securdeproject.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

@Service
public class UserService {
    /* Repositories */
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    /* Services */
    @Autowired
    private SecurityService securityService;

    /* Encryptor */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private MessageSource messages;

    /***
     ***
        DATABASE SERVICES
     ***
     ***/

    /*** Save New User ***/
    public User saveNewUser(UserDto u, ArrayList<Role> roles)
    {
        /* Transfer to User object */
        User user = new User();
        user.setBirthdate(u.getBirthdate());
        user.setEmail(u.getEmail());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setMailAddress(u.getMailAddress());
        user.setMobileNumber(u.getMobileNumber());
        user.setUsername(u.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        user.setRoles(new HashSet<>(roles));

        saveUser(user);
        return user;
    }

    /*** Save/Update User into Database ***/
    public void saveUser(User u)
    {
        userRepository.save(u);
    }

    /*** Retrieve User by username ***/
    public User findUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    /*** Retrieve User by email ***/
    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    /*** Retrieve all Users ***/
    public Iterator findAllUsers()
    {
        ArrayList<User> allUsers = (ArrayList<User>) userRepository.findAll();
        return allUsers.iterator();
    }

    /*** Update Password ***/
    public void saveNewPassword(User user, String password)
    {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    /*** Add Role ***/
    public void saveRole(Role r)
    {
        roleRepository.save(r);
    }

    /*** Retrieve Role Name ***/
    public Role findRoleByName(String name)
    {
        return roleRepository.findByName(name);
    }

    /***
     ***
     OTHER FUNCTIONS
     ***
     ***/

    /*** Retrieve current user ***/
    public User retrieveUser()
    {
        String username = securityService.findLoggedInUsername();
        return findUserByUsername(username);
    }

    /*** Template URL ***/
    public String getAppUrl(HttpServletRequest request)
    {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    /*** Construct Template Email ***/
    public SimpleMailMessage constructEmail(String subject, String message, User user)
    {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(message);
        email.setTo(user.getEmail());
        return email;
    }

    /*** Construct Specific Emails ***/
    public SimpleMailMessage constructVerificationTokenEmail(String contextPath, String newToken, User user)
    {
        String confirmationUrl = contextPath + "/signup-confirm?token=" + newToken;
        String message = messages.getMessage("message.registerSuccess", null, null);
        return constructEmail("Confirm Registration at Troy's Toys", message + " \r\n" + confirmationUrl, user);
    }

    public SimpleMailMessage constructResetTokenEmail(String contextPath, String newToken, User user)
    {
        String confirmationUrl = contextPath + "/change-password?id=" + user.getUserId() +
                "&token=" + newToken;
        String message = messages.getMessage("message.resetPassword", null, null);
        return constructEmail("Reset Password For Troy's Toys", message + " \r\n" + confirmationUrl, user);
    }
}
