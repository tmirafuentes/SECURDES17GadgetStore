package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.repositories.TransactionRepository;
import edu.dlsu.securdeproject.services.CustomerService;
import edu.dlsu.securdeproject.services.ProductService;
import edu.dlsu.securdeproject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TransactionController {
	/* Repositories (For Database Access) */
	@Autowired
	private ProductRepository transactionRepository;

	/* Services */
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private TransactionService transactionService;

	@RequestParameter(value="/buyProduct", method=RequestMethod.POST)
	public String buyProduct(Model model, @ModelAttribute("product") Product product, 
							 @RequestParam("username") String username, @RequestParam("qty") int quantity) 
	{
		/* Retrieves product and customer from hidden forms of model */
		Product p = product;
		Customer c = customerService.findByUsername(username);

		/* Creates new Transaction */
		Transaction t = new Transaction();
		t.setCustomer(c);
		t.setProduct(p);
		t.setQuantity(quantity);
		t.setTotalAmount(p.getPrice() * quantity);
		t.setTimestamp(Calendar.getInstance());
		t.setStatus(true);

		/* Put thank you page */
		if (transactionService.addNewTransaction(t))
			return "success";
	}
}