package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByProductType(String productType);
    List<Product> findAllByProductNameContains(String productString);
}