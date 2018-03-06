package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Admin;

public interface AdService {
    void save(Admin admin);

    Admin findByUsername(String username);
}
