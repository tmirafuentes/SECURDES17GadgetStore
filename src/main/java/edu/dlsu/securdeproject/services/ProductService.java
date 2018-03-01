package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Product;
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

    public Product getProduct(long productId) {
        return (Product) productRepository.findByProductId(productId);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Product> getAllProducts(String productName) {
        return (List<Product>) productRepository.findAllByProductName(productName);
    }

    public boolean deleteProduct(Product p) {
        productRepository.delete(p);
        return true;
    }
}
