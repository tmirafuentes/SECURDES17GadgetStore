package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public boolean addNewProduct(Product p) {
        productRepository.save(p);
        return true;
    }

    public boolean updateProduct(Product p) {
        productRepository.save(p);
        return true;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
}
