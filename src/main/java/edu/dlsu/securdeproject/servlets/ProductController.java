package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import edu.dlsu.securdeproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ProductController {
    /* Repositories (For Database Access) */
    @Autowired
    private ProductRepository productRepository;

    /* Services */
    @Autowired
    private ProductService productService;

    // Add Product Page
    @RequestMapping(value="/addProduct", method=RequestMethod.GET)
    public String createProductPage() {
        return "createProduct";
    }

    // Adds new product
    @RequestMapping(value="/addProduct", method=RequestMethod.POST)
    public String createProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult, Model model) {
        /* Later na yung validation */

        /* If error, create product again */
        if (bindingResult.hasErrors())
            return "createProduct";

        productService.addNewProduct(prodForm);

        return "createProduct";
    }

    /* Search Product Functionality */
    /* Redirects to index but with also search results messages */
    /* Has to be exact for now */
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String searchProduct(Model model, @RequestParam String productName) {
        // Will validate productName later
        ArrayList<Product> searchResults = (ArrayList<Product>) productService.getAllProducts(productName);
        String message = "There are " + searchResults.size() + " results for '" + productName + "'.";

        model.addAttribute("products", searchResults);
        model.addAttribute("searchMessage", message);

        return "index";
    }

    /* Deletes a Product */
    @RequestMapping(value="/deleteProduct", method=RequestMethod.POST)
    public String deleteProduct(Model model, @RequestParam long productId) {
        Product product = productService.getProduct(productId);

        if (productService.deleteProduct(product))
            model.addAttribute("products", productService.getAllProducts());

        return "admin";
    }
}