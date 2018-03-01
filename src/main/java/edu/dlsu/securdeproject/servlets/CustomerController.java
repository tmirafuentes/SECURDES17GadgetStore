package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import edu.dlsu.securdeproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {
    /* Repositories (For Database Access) */
    @Autowired
    private CustomerRepository customerRepository;

    /* Services */
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ProductService productService;

	/* Default Homepage */
    @RequestMapping(value = {"/welcome", "/index" , "/"}, method=RequestMethod.GET)
    public String index(ModelMap model) {
        System.out.println(productService.getAllProducts().size());
        model.put("products", productService.getAllProducts());

        return "index";
    }

    /* Default Error Page */
    //@RequestMapping("/error")
    //public String error() {
    	//return "error";
    //}

    /* Creation/Registration of Account */
    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signUpPage(Model model) {
    	model.addAttribute("custForm", new Customer());
        return "signup";
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signUpSubmit(@ModelAttribute("custForm") Customer custForm, BindingResult bindingResult, Model model) {
    	/* Validation of form */
    	validationService.validate(custForm, bindingResult);

    	/* If error, create account again */
    	if (bindingResult.hasErrors()) {
    	    return "signup";
        }

    	/* Else, save new account to the database */
    	userService.save(custForm);

    	/* Keep user logged in after registering */
    	securityService.autologin(custForm.getUsername(), custForm.getPasswordConfirm());

        return "index";
    }

    @RequestMapping(value = {"/signin"}, method = RequestMethod.GET)
    public String signInPage(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Either your username or password is invalid. Please try again.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signInSubmit(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        securityService.autologin(username, password);

        return "index";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String editAccountPage(Model model) {
        return "accountSettings";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String editAccountSubmit(@ModelAttribute("custForm") Customer custForm, BindingResult bindingResult, Model model) {
     	/* Validation of form */
    	validationService.validate(custForm, bindingResult);

    	/* If error, create account again */
    	if (bindingResult.hasErrors())
    		return "account";

    	/* Else, save new account to the database */
    	userService.save(custForm);

        return "account";   	
    }
}