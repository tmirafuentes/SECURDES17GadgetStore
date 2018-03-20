package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUserId(Long userId);
	User findByUsername(String username);
	User findByEmail(String email);
}