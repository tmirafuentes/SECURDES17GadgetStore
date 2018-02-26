package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}