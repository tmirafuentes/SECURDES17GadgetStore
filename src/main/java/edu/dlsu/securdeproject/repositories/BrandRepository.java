package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Brand;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findByBrandName(String brandName);
}
