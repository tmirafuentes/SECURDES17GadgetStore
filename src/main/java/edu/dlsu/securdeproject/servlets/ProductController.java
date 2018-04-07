package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Brand;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Type;
import edu.dlsu.securdeproject.classes.dtos.ProductDto;
import edu.dlsu.securdeproject.security.SecurityService;
import edu.dlsu.securdeproject.services.MainService;
import edu.dlsu.securdeproject.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Controller
public class ProductController {
    /*** Services ***/
    @Autowired
    private MainService mainService;
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

        return "add-product";
    }

    @RequestMapping(value = "/admin/add-product", method = RequestMethod.POST)
    public String addProductSubmit(@ModelAttribute("prodForm") @Valid ProductDto prodForm, BindingResult bindingResult, Model model)
    {
        /* Errors */
        if (bindingResult.hasErrors())
            return "add-product";

        /* Else, save new product to the database */
        mainService.saveProduct(prodForm);

        model.addAttribute("message", messages.getMessage("message.addProductSuccess", null, null));
        return "redirect:/admin";
    }

    /*** Edit Product Details ***/
    @RequestMapping(value = "/admin/edit-product", method = RequestMethod.GET)
    public String editProductPage(Model model, @RequestParam("prodId") Long prodId)
    {
        /* Find Specific Product */
        Product currProd = mainService.findProductByProductId(prodId);
        currProd.setProductId(prodId);

        /* Generate Form */
        model.addAttribute("prodForm", currProd);
        model.addAttribute("prodTypes", generateProductTypes());
        model.addAttribute("prodBrands", generateProductBrands());

        return "edit-product";
    }

    @RequestMapping(value = "/admin/edit-product", method = RequestMethod.GET)
    public String editProductSubmit(@ModelAttribute("prodForm") @Valid ProductDto prodForm, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
            return "edit-product";

        model.addAttribute("message", messages.getMessage("message.editProductSuccess", null, null));
        return "redirect:/admin";
    }

    /*** Filter Products ***/
    @RequestMapping(value = "/desktops", method = RequestMethod.GET)
    public String getDesktops(Model model) {
        model.addAttribute("allProducts", mainService.findProductsByType("Desktop"));
        return "index";
    }

    public String getLaptops(Model model) {
        model.addAttribute("allProducts", mainService.findProductsByType("Laptop"));
        return "index";
    }

    public String getTablets(Model model) {
        model.addAttribute("allProducts", mainService.findProductsByType("Tablet"));
        return "index";
    }

    public String getMobiles(Model model) {
        model.addAttribute("allProducts", mainService.findProductsByType("Mobile"));
        return "index";
    }


    /***
     ***
        OTHER FUNCTIONS
     ***
     ***/

    private Iterator generateProductTypes() {
        ArrayList<Type> productTypes = mainService.findAllProductTypes();
        return productTypes.iterator();
    }

    private Iterator generateProductBrands() {
        ArrayList<Brand> productBrands = mainService.findAllProductBrands();
        return productBrands.iterator();
    }
}
