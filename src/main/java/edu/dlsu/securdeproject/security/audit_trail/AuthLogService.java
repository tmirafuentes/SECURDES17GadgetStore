package edu.dlsu.securdeproject.security.audit_trail;

import edu.dlsu.securdeproject.repositories.AuthLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AuthLogService implements LoggingInterface {
    @Autowired
    private AuthLogRepository authLogRepository;

    public void createNewLog(String username, boolean success, String ipAddress)
    {
        AuthLog newLog = new AuthLog();
        newLog.setUsername(username);
        newLog.setSuccess(success);
        newLog.setIpAddress(ipAddress);
        newLog.setTimestamp(Calendar.getInstance());

        submitNewLog(newLog);
    }

    @Override
    public void submitNewLog(Object obj)
    {
        AuthLog newLog = (AuthLog) obj;
        authLogRepository.save(newLog);
    }
}
