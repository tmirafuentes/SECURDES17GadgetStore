package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Admin;
import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.repositories.AdminRepository;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class AdminService implements UserDetailsService, AdService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* Authentication and Authorization Services */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //for (Role role : customer.getRoles())
        //grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));


        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), grantedAuthorities);
    }

    @Override
    public void save(Admin c) {
        c.setPassword(bCryptPasswordEncoder.encode(c.getPassword()));
        adminRepository.save(c);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
