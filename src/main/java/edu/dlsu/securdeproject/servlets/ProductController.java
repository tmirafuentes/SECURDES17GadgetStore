package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import edu.dlsu.securdeproject.services.CustomerService;
import edu.dlsu.securdeproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
    /* Repositories (For Database Access) */
    @Autowired
    private ProductRepository productRepository;

    /* Services */
    @Autowired
    private ProductService productService;

    @RequestMapping(value="/createProduct", method=RequestMethod.GET)
    public String createProductPage() {
        return "createProduct";
    }

    @RequestMapping(value="/createProduct", method=RequestMethod.POST)
    public String addNewAccount(ModelMap model, @RequestParam String productName, @RequestParam double productPrice) {
        Product p = new Product();
        p.setProductName(productName);
        p.setProductPrice(productPrice);

        boolean isValidProduct = productService.addNewProduct(p);

        if (isValidProduct) {
            model.put("products", productService.getAllProducts());

            return "index";
        }

        return "createProduct";
    }
}