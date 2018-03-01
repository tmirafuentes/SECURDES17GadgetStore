package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
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

import java.util.Calendar;

@Controller
public class TransactionController {
	/* Services */
	@Autowired
	private ProductService productService;
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value="/buyProduct", method=RequestMethod.POST)
	public String buyProduct(Model model, @ModelAttribute("product") Product product,
							 @RequestParam("username") Customer customer, @RequestParam("qty") int quantity)
	{
		/* Retrieves product and customer from hidden forms of model */
		Product p = product;
		Customer c = customer;

		/* Creates new Transaction */
		Transaction t = new Transaction();
		t.setCustomer(c);
		t.setProduct(p);
		t.setQuantity(quantity);
		t.setTotalAmount(p.getProductPrice() * quantity);
		t.setTimestamp(Calendar.getInstance());
		t.setStatus(true);

		transactionService.addNewTransaction(t);

		/* Put thank you page */
		return "success";
	}
}