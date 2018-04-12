package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.security.audit_trail.AuthLog;
import org.springframework.data.repository.CrudRepository;

public interface AuthLogRepository extends CrudRepository<AuthLog, Long> {
}
