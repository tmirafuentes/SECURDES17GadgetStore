package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Admin;
import edu.dlsu.securdeproject.classes.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByUsername(String username);
}