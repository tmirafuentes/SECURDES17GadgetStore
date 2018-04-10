package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Brand;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Type;
import edu.dlsu.securdeproject.classes.dtos.ProductDto;
import edu.dlsu.securdeproject.repositories.BrandRepository;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import edu.dlsu.securdeproject.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    /* Repositories */
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TypeRepository typeRepository;

    /***
     ***
        DATABASE SERVICES
     ***
     ***/

    /* Save Product */
    public void saveProduct(ProductDto p) {
        Product newProd = new Product();
        newProd.setProductName(p.getProductName());
        newProd.setProductPrice(p.getProductPrice());
        newProd.setProductQuantity(p.getProductQuantity());
        newProd.setProductDescription(p.getProductDescription());
        newProd.setProductBrand(findBrandByName(p.getProductBrand()));
        newProd.setProductType(findTypeByName(p.getProductType()));
        productRepository.save(newProd);
    }

    public void saveProduct(Product p) {
        productRepository.save(p);
    }

    /* Retrieve specific product */
    public Product findProductByProductId(Long productId) {
        return (Product) productRepository.findByProductId(productId);
    }

    /* Retrieve all products */
    public ArrayList<Product> findAllProducts() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    /* Retrieve products by specific type */
    public ArrayList<Product> findProductsByType(String productType) {
        return (ArrayList<Product>) productRepository.findAllByProductType(findTypeByName(productType));
    }

    /* Retrieve products by search */
    public ArrayList<Product> findProductsBySearch(String productName) {
        return (ArrayList<Product>) productRepository.findAllByProductNameContains(productName);
    }

    /* Delete a product */
    public void deleteProduct(Product p) {
        productRepository.delete(p);
    }

    /* Retrieve all product brands */
    public ArrayList<Brand> findAllProductBrands() {
        return (ArrayList<Brand>) brandRepository.findAll();
    }

    public Brand findBrandByName(String brandName) {
        return brandRepository.findByBrandName(brandName);
    }

    public ArrayList<Type> findAllProductTypes() {
        return (ArrayList<Type>) typeRepository.findAll();
    }

    public Type findTypeByName(String typeName) {
        return typeRepository.findByTypeName(typeName);
    }

    /* Update Product Quantity */
    public void updateProductQuantity(Product p, int quantity) {
        p.setProductQuantity(p.getProductQuantity() - quantity);
        productRepository.save(p);
    }
}

