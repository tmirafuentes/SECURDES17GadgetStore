package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import edu.dlsu.securdeproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {
    /* Repositories (For Database Access) */
    @Autowired
    private CustomerRepository customerRepository;

    /* Services */
    @Autowired
    private CustomerService customerService;

	/* Default Homepage */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String createAccountPage() {
        return "create";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String addNewAccount(ModelMap model, @RequestParam String firstName, @RequestParam String lastName) {
        Customer c = new Customer();
        c.setFirstName(firstName);
        c.setLastName(lastName);

        boolean isValidUser = customerService.addNewCustomer(c);

        if (isValidUser) {
            model.put("customers", customerService.getAllCustomers());

            return "hello";
        }

        return "login";
    }

    @RequestMapping(value="/update", method=RequestMethod.GET)
    public String updateAccountPage() {
        return "update";
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String updateAccount(ModelMap model, @RequestParam long userId, @RequestParam String firstName, @RequestParam String lastName) {
        Customer c = new Customer();
        c.setUserId(userId);
        c.setFirstName(firstName);
        c.setLastName(lastName);

        boolean isValidUser = customerService.updateCustomer(c);

        if (isValidUser) {
            model.put("firstName", firstName);
            model.put("lastName", lastName);

            return "hello";
        }

        return "update";
    }

    @RequestMapping(value="/add", method= RequestMethod.GET)
    public @ResponseBody
    String addNewCustomer(@RequestParam String firstName, @RequestParam String lastName) {
    	Customer c = new Customer();
    	c.setFirstName(firstName);
    	c.setLastName(lastName);
    	customerRepository.save(c);
    	return "Saved";
    }

    @RequestMapping(value="/retrieveAll", method= RequestMethod.GET)
    public @ResponseBody Iterable<Customer> getAllCustomers() {
    	return customerRepository.findAll();
    }
}