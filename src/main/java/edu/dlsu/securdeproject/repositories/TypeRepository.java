package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {
    Type findByTypeName(String typeName);
}
