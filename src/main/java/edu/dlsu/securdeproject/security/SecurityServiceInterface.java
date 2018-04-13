package edu.dlsu.securdeproject.security;

public interface SecurityServiceInterface {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
