package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

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
}
