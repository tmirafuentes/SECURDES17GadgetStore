package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductId(long productId);
    List<Product> findAllByProductBrand(String productBrand);
    List<Product> findAllByProductNameContains(String productString);
}