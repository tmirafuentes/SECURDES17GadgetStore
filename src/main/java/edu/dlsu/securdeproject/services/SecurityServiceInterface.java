package edu.dlsu.securdeproject.services;

public interface SecurityServiceInterface {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
