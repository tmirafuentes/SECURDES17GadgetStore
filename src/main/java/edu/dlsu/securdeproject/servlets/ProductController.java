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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
    private MessageSource messages;
    private HttpServletRequest httpServletRequest;

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

        //model.addAttribute("message", messages.getMessage("message.addProductSuccess", null, null));
        return "redirect:/admin";
    }

    /*** Edit Product Details ***/
    @RequestMapping(value = "/admin/edit-product", method = RequestMethod.GET)
    public String editProductPage(Model model, @RequestParam("v") String linkId)
    {
        /* Find Specific Product */
        Product currProd = productService.findProductByLink(linkId);
        if (currProd == null)
            return "redirect:/error";

        /* Transfer to DTO first */
        ProductDto prodForm = new ProductDto();
        prodForm.setProductBrand(currProd.getProductBrand().getBrandName());
        prodForm.setProductDescription(currProd.getProductDescription());
        prodForm.setProductName(currProd.getProductName());
        prodForm.setProductPrice(currProd.getProductPrice());
        prodForm.setProductQuantity(currProd.getProductQuantity());
        prodForm.setProductType(currProd.getProductType().getTypeName());
        prodForm.setLinkId(linkId);

        /* Generate Form */
        model.addAttribute("product", currProd);
        model.addAttribute("prodForm", prodForm);
        model.addAttribute("prodTypes", generateProductTypes());
        model.addAttribute("prodBrands", generateProductBrands());

        return "product/edit-product";
    }

    @RequestMapping(value = "/admin/edit-product", method = RequestMethod.POST)
    public String editProductSubmit(@ModelAttribute("prodForm") @Valid ProductDto prodForm,
                                    BindingResult bindingResult, Model model, @RequestParam("v") String link)
    {
        /* Check if there are errors */
        if (bindingResult.hasErrors()) {
            for(FieldError error: bindingResult.getFieldErrors())
                System.out.println(error.getObjectName() + " - " + error.getField() + " - " + error.getDefaultMessage());

            model.addAttribute("prodTypes", generateProductTypes());
            model.addAttribute("prodBrands", generateProductBrands());
            return "product/edit-product";
        }
        Product p = productService.findProductByLink(link);

        /* Merge with current product */
        p.setProductQuantity(prodForm.getProductQuantity());
        p.setProductPrice(prodForm.getProductPrice());
        p.setProductDescription(prodForm.getProductDescription());
        p.setProductName(prodForm.getProductName());
        p.setProductType(productService.findTypeByName(prodForm.getProductType()));
        p.setProductBrand(productService.findBrandByName(prodForm.getProductBrand()));
        productService.saveProduct(p);

        //model.addAttribute("message", messages.getMessage("message.editProductSuccess", null, null));
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
    public String deleteProduct(Model model, @RequestParam("v") String viewId)
    {
        Product p = productService.findProductByLink(viewId);
        productService.deleteProduct(p);

        //model.addAttribute("message", messages.getMessage("message.deleteProduct", null, null));
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
        ArrayList<String> typeNames = new ArrayList<String>();
        for(Type type: productService.findAllProductTypes())
            typeNames.add(type.getTypeName());
        return typeNames.iterator();
    }

    private Iterator generateProductBrands() {
        ArrayList<String> brandNames = new ArrayList<String>();
        for(Brand brand: productService.findAllProductBrands())
            brandNames.add(brand.getBrandName());
        return brandNames.iterator();
    }
}
