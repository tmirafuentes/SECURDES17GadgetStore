package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Brand;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @RequestMapping(value={"/addProduct"}, method=RequestMethod.GET)
    public String createProductPage(Model model) {
        ArrayList<String> productTypes = new ArrayList<String>();
        productTypes.add("Desktop");
        productTypes.add("Laptop");
        productTypes.add("Tablet");
        productTypes.add("Mobile");

        ArrayList<String> productBrands = new ArrayList<String>();
        productBrands.add("Acer");
        productBrands.add("Asus");
        productBrands.add("Lenovo");

        model.addAttribute("prodForm", new Product());
        model.addAttribute("prodTypes", productTypes);
        model.addAttribute("prodBrands", productBrands);
        return "addProduct";
    }

    // Adds new product
    @RequestMapping(value="/addProduct", method=RequestMethod.POST)
    public String createProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult,
                                      Model model)
    {
        /* Later na yung validation */

        /* Process file */
        //storageService.store(file);
        //redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

        /* If error, create product again */
        if (bindingResult.hasErrors()) {
            ArrayList<String> productTypes = new ArrayList<String>();
            productTypes.add("Desktop");
            productTypes.add("Laptop");
            productTypes.add("Tablet");
            productTypes.add("Mobile");

            ArrayList<String> productBrands = new ArrayList<String>();
            productBrands.add("Acer");
            productBrands.add("Asus");
            productBrands.add("Lenovo");

            model.addAttribute("prodForm", new Product());
            model.addAttribute("prodTypes", productTypes);
            model.addAttribute("prodBrands", productBrands);
            return "addProduct";
        }

        productService.addNewProduct(prodForm);

        return "index";
    }

    @RequestMapping(value={"/editProduct"}, method=RequestMethod.GET)
    public String editProductPage(Model model) {
        ArrayList<String> productTypes = new ArrayList<String>();
        productTypes.add("Desktop");
        productTypes.add("Laptop");
        productTypes.add("Tablet");
        productTypes.add("Mobile");

        ArrayList<String> productBrands = new ArrayList<String>();
        productBrands.add("Acer");
        productBrands.add("Asus");
        productBrands.add("Lenovo");

        model.addAttribute("prodForm", new Product());
        model.addAttribute("prodTypes", productTypes);
        model.addAttribute("prodBrands", productBrands);
        return "addProduct";
    }

    /* Search Product Functionality */
    /* Redirects to index but with also search results messages */
    /* Has to be exact for now */
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String searchProduct(Model model, @RequestParam String productName) {
        // Will validate productName later
        ArrayList<Product> searchResults = (ArrayList<Product>) productService.getAllProductsBySearch(productName);
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

    @RequestMapping(value="/desktops", method=RequestMethod.GET)
    public String getDesktops(ModelMap model)
    {
        model.put("products", productService.getAllProductsByType("Desktop"));
        return "index";
    }

    @RequestMapping(value="/laptops", method=RequestMethod.GET)
    public String getLaptops(ModelMap model)
    {
        model.put("products", productService.getAllProductsByType("Laptop"));
        return "index";
    }

    @RequestMapping(value="/tablets", method=RequestMethod.GET)
    public String getTablets(ModelMap model)
    {
        model.put("products", productService.getAllProductsByType("Tablet"));
        return "index";
    }

    @RequestMapping(value="/mobiles", method=RequestMethod.GET)
    public String getMobiles(ModelMap model)
    {
        model.put("products", productService.getAllProductsByType("Mobile"));
        return "index";
    }

    @RequestMapping(value="/getProductsBySearch", method=RequestMethod.GET)
    public String getProdSearch(ModelMap model, @RequestParam String productString)
    {
        model.put("products", productService.getAllProductsBySearch(productString));
        return "index";
    }
}