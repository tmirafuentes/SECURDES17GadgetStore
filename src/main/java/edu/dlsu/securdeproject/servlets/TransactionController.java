package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.repositories.CustomerRepository;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import edu.dlsu.securdeproject.repositories.TransactionRepository;
import edu.dlsu.securdeproject.services.CustomerService;
import edu.dlsu.securdeproject.services.ProductService;
import edu.dlsu.securdeproject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Calendar;

@Controller
public class TransactionController {
	/* Services */
	@Autowired
	private ProductService productService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
    private CustomerRepository customerRepository;

	@RequestMapping(value="/buyProduct", method=RequestMethod.POST)
	public String buyProduct(Model model, @ModelAttribute("prodId") long productId,
							 @RequestParam("prodQty") int quantity, @RequestParam("custName") String username)
	{
		/* Retrieves product and customer from hidden forms of model */
		Product p = productService.getProduct(productId);
		Customer c = customerRepository.findByUsername(username);
		System.out.println("Name = " + username);

		/* Creates new Transaction */
		Transaction t = new Transaction();
		t.setCustomer(c);
		t.setProduct(p);
		t.setQuantity(quantity);
		t.setTotalAmount(p.getProductPrice() * quantity);
		t.setTimestamp(Calendar.getInstance());
		t.setStatus(true);

		transactionService.addNewTransaction(t);

		p.setProductQuantity(p.getProductQuantity() - quantity);
		productService.updateProduct(p);

		/* Put thank you page */
		return "adminHome";
	}
}