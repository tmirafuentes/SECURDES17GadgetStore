package edu.dlsu.securdeproject.security.audit_trail;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class AuthLog {
    private long authId;
    private String username;
    private Calendar timestamp;
    private String ipAddress;
    private boolean success;

    public AuthLog() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getAuthId() {
        return authId;
    }

    public void setAuthId(long authId) {
        this.authId = authId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
