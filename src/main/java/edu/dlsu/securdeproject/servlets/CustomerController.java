package edu.dlsu.securdeproject.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	/* Default Homepage */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewCustomer(@RequestParam String firstName, @RequestParam String lastName) {
    	Customer c = new Customer();
    	c.setFirstName(firstName);
    	c.setLastName(lastName);
    	customerRepository.save(c);
    	return "Saved";
    }

    @GetMapping(path="/get")
    public @ResponseBody Customer getCustomer(@RequestParam int id) {
    	return customerRepository.findById(id);
    }

    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Customer> getAllCustomers() {
    	return customerRepository.findAll();
    }

    @GetMapping(path="/update")
    public @ResponseBody String updateCustomer(@RequestParam int id, @RequestParam String firstName) {
    	Customer c = customerRepository.findById(id);
    	c.setFirstName(firstName);
    	customerRepository.save(c);
    	return "Updated";
    }

    @GetMapping(path="/delete")
    public @ResponseBody String deleteCustomer(@RequestParam int id) {
    	customerRepository.deleteById(id);
    	return "Deleted";
    }
}