package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.security.audit_trail.AuthLog;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Calendar;

public interface AuthLogRepository extends CrudRepository<AuthLog, Long> {
    ArrayList<AuthLog> findByUsernameOrderByTimestampDesc(String username);
}
