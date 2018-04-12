package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Brand;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Type;
import edu.dlsu.securdeproject.classes.dtos.ProductDto;
import edu.dlsu.securdeproject.security.SecurityService;
import edu.dlsu.securdeproject.services.ProductService;
import edu.dlsu.securdeproject.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
@SessionAttributes("product")
public class ProductController {
    /*** Services ***/
    @Autowired
    private ProductService productService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private SecurityService securityService;

    /*** Extra Stuff ***/
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    private MessageSource messages;

    /***
     ***
        URL MAPPING
     ***
     ***/

    /*** Add Products ***/
    @RequestMapping(value = "/admin/add-product", method = RequestMethod.GET)
    public String addProductPage(Model model)
    {
        model.addAttribute("prodForm", new Product());
        model.addAttribute("prodTypes", generateProductTypes());
        model.addAttribute("prodBrands", generateProductBrands());

        return "product/add-product";
    }

    @RequestMapping(value = "/admin/add-product", method = RequestMethod.POST)
    public String addProductSubmit(@ModelAttribute("prodForm") @Valid ProductDto prodForm, BindingResult bindingResult, Model model)
    {
        /* Errors */
        if (bindingResult.hasErrors())
            return "product/add-product";

        /* Else, save new product to the database */
        productService.saveProduct(prodForm);

        model.addAttribute("message", messages.getMessage("message.addProductSuccess", null, null));
        return "redirect:/admin";
    }

    /*** Edit Product Details ***/
    @RequestMapping(value = "/admin/edit-product", method = RequestMethod.GET)
    public String editProductPage(Model model, @RequestParam("prodId") Long prodId)
    {
        /* Find Specific Product */
        Product currProd = productService.findProductByProductId(prodId);
        currProd.setProductId(prodId);

        /* Generate Form */
        model.addAttribute("prodForm", currProd);
        model.addAttribute("prodTypes", generateProductTypes());
        model.addAttribute("prodBrands", generateProductBrands());

        return "product/edit-product";
    }

    @RequestMapping(value = "/admin/edit-product", method = RequestMethod.POST)
    public String editProductSubmit(@ModelAttribute("prodForm") @Valid ProductDto prodForm, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
            return "product/edit-product";

        model.addAttribute("message", messages.getMessage("message.editProductSuccess", null, null));
        return "redirect:/admin";
    }

    /*** Filter Products ***/
    @RequestMapping(value = "/desktops", method = RequestMethod.GET)
    public String getDesktops(Model model)
    {
        model.addAttribute("allProducts", productService.findProductsByType("Desktop"));
        return "product/category";
    }

    @RequestMapping(value = "/laptops", method = RequestMethod.GET)
    public String getLaptops(Model model)
    {
        model.addAttribute("allProducts", productService.findProductsByType("Laptop"));
        return "product/category";
    }

    @RequestMapping(value = "/tablets", method = RequestMethod.GET)
    public String getTablets(Model model)
    {
        model.addAttribute("allProducts", productService.findProductsByType("Tablet"));
        return "product/category";
    }

    @RequestMapping(value = "/mobiles", method = RequestMethod.GET)
    public String getMobiles(Model model)
    {
        model.addAttribute("allProducts", productService.findProductsByType("Mobile"));
        return "product/category";
    }

    /*** View a Product ***/
    @RequestMapping(value = "/view-product", method = RequestMethod.GET)
    public String viewProductPage(Model model, @RequestParam("v") String viewId)
    {
        Product p = productService.findProductByLink(viewId);
        if (p == null)
            return "redirect:/error";

        model.addAttribute("product", p);
        return "product/view-product";
    }

    /*** Delete a Product ***/
    @RequestMapping(value = "/admin/delete-product", method = RequestMethod.GET)
    public String deleteProduct(Model model, @RequestParam("prodId") long prodId)
    {
        Product p = productService.findProductByProductId(prodId);
        productService.deleteProduct(p);

        model.addAttribute("message", messages.getMessage("message.deleteProduct", null, null));
        return "redirect:/admin";
    }

    /*** Search for a Product ***/
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String viewProductResults(Model model, @RequestParam("searchString") String searchString) {

        model.addAttribute("allProducts", productService.findProductsBySearch(searchString));
        return "product/category";
    }

    /***
     ***
        OTHER FUNCTIONS
     ***
     ***/

    private Iterator generateProductTypes() {
        ArrayList<Type> productTypes = productService.findAllProductTypes();
        return productTypes.iterator();
    }

    private Iterator generateProductBrands() {
        ArrayList<Brand> productBrands = productService.findAllProductBrands();
        return productBrands.iterator();
    }
}
