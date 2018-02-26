package edu.dlsu.securdeproject.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.dlsu.securdeproject.classes.Customer;

public interface CrudRepository extends CrudRepository<Customer, Long> {
	
}