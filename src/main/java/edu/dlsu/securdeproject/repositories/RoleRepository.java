package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}