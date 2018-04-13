package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.security.registration.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
	VerificationToken findByToken(String token);
	User findByUser(User user);
} 