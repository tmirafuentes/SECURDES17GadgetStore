package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Brand;
import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import edu.dlsu.securdeproject.services.CustomerService;
import edu.dlsu.securdeproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ProductController {
    /* Repositories (For Database Access) */
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

        model.addAttribute("products", productService.getAllProducts());

        return "adminHome";
    }

    @RequestMapping(value="/editProduct", method=RequestMethod.GET)
    public String editProductPage(Model model, @RequestParam("prodId") long prodId) {
        ArrayList<String> productTypes = new ArrayList<String>();
        productTypes.add("Desktop");
        productTypes.add("Laptop");
        productTypes.add("Tablet");
        productTypes.add("Mobile");

        ArrayList<String> productBrands = new ArrayList<String>();
        productBrands.add("Acer");
        productBrands.add("Asus");
        productBrands.add("Lenovo");

        Product p = productService.getProduct(prodId);
        p.setProductId(prodId);

        model.addAttribute("prodForm", p);
        model.addAttribute("prodId", prodId);
        model.addAttribute("prodTypes", productTypes);
        model.addAttribute("prodBrands", productBrands);

        return "editProduct";
    }

    @RequestMapping(value={"/editProduct"}, method=RequestMethod.POST)
    public String editProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult,
                                    ModelMap model) {

        System.out.println("sid = " + prodForm.getProductId());
        productService.updateProduct(prodForm);
        model.put("products", productService.getAllProducts());

        return "adminHome";
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
    @RequestMapping(value="/deleteProduct", method=RequestMethod.GET)
    public String deleteProduct(Model model, @RequestParam("prodId") long prodId) {
        Product product = productService.getProduct(prodId);

        if (productService.deleteProduct(product))
            model.addAttribute("products", productService.getAllProducts());

        return "adminHome";
    }

    /* Views a Product */
    @RequestMapping(value="/viewProduct", method=RequestMethod.GET)
    public String viewProduct(Model model, @RequestParam("prodId") long prodId) {
        Product product = productService.getProduct(prodId);

        model.addAttribute("indiProd", product);

        return "product";
    }

    @RequestMapping(value="/confirmation", method=RequestMethod.POST)
    public String confirmPurchase(Model model, @RequestParam("prodId") long prodId,
                                  @RequestParam("prodQty") int prodQty, Principal principal) {
        System.out.println("Prod = " + prodId);
        Product product = productService.getProduct(prodId);
        model.addAttribute("indiProd", product);
        model.addAttribute("prodQty", prodQty);

        Customer customer = customerRepository.findByUsername(principal.getName());
        model.addAttribute("currCust", customer);
        return "confirmation";
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