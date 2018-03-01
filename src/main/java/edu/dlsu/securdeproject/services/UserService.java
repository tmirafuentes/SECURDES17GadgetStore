package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Admin;
import edu.dlsu.securdeproject.classes.Customer;

public interface UserService {
    void save(Customer customer);

    Customer findByUsername(String username);
}
