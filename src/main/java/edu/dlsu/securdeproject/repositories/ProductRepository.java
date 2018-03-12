package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductId(Long productId);
    List<Product> findAllByProductType(String productType);
    List<Product> findAllByProductNameContains(String productString);

    Product findByProductId(Long productId);
}