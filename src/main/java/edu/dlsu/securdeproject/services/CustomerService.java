package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Admin;
import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.repositories.AdminRepository;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService implements UserDetailsService, UserService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* Database Services */
    public boolean addNewCustomer(Customer c) {
        customerRepository.save(c);
        return true;
    }

    public boolean updateCustomer(Customer c) {
        customerRepository.save(c);
        return true;
    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    /* Authentication and Authorization Services */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = null;
        Customer customer = null;
        boolean aOrC = true;
        if (customerRepository.findByUsername(username) == null) {
            admin = adminRepository.findByUsername(username);
            aOrC = false;
        } else
            customer = customerRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //for (Role role : customer.getRoles())
        //grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

        if (aOrC)
            return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), grantedAuthorities);

        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), grantedAuthorities);
    }

    @Override
    public void save(Customer c) {
        c.setPassword(bCryptPasswordEncoder.encode(c.getPassword()));
        customerRepository.save(c);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
