package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.security.password_recovery.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends CrudRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
    User findByUser(User user);
}
