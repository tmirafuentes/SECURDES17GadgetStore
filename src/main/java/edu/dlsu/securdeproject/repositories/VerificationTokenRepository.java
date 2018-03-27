package edu.dlsu.securdeproject.repositories;

@Repository
public interface VerificationTokenRepository extends CrudRepository<Token, Long> {
	Token findByToken(VerificationToken token);
	User findByUser(User user);
} 