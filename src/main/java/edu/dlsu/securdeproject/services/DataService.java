package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class DataService implements ApplicationRunner {
    private UserService userService;

    @Autowired
    public DataService(UserService userService) {
        this.userService = userService;
    }

    public void run(ApplicationArguments args) {
        /* Initialize Admin */
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(userService.findRoleByName("ROLE_USER"));
        roles.add(userService.findRoleByName("ROLE_ADMIN"));

        User admin = new User();
        admin.setUsername("Admin");
        admin.setEmail("mark_gavin_sanchez@dlsu.edu.ph");
        admin.setRoles(new HashSet<>(roles));
        admin.setEnabled(true);
        userService.saveUser(admin);
        userService.saveNewPassword(admin, "password123");
    }
}
