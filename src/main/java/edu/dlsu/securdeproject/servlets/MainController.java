package.edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.repositories.UserRepository;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import edu.dlsu.securdeproject.repositories.TransactionRepository;
import edu.dlsu.securdeproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Calendar;

@Controller
public class MainController {
	/* All Services and Repositories */
	@Autowired
	private MainService mainService;
	@Autowired
	private ValidationService validationService;

	/* Default Homepage */
	@RequestMapping(value = {"/", "/welcome", "/index"}, method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("allProducts", mainService.findAllProducts());

		return "index";
	}

	/***** USER ACTIONS *****/

	/* Registration of Account */
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String signUpPage(Model model) {
		model.addAttribute("userForm", new User());

		return "signup";
	}

	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public String signUpSubmit(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		/* Validates Form Submitted */
		validationService.validate(userForm, bindingResult);
	
		/* If error, redirect to sign up again */
		if (bindingResult.hasError())
			return "signup";

		/* Else, save new account to the database */
		List<Role> roles = new List<Role>();
		roles.add(mainService.findRoleByName("ROLE_USER"));
		mainService.saveUser(userForm, roles);

		/* Keep user logged in after registering */
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "index";
	}

	/* Sign In */
	@RequestMapping(value = "/signin", method=RequestMethod.GET)
	public String signInPage(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Either your username or password is invalid. Please try again.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "signin";
	}

	@RequestMapping(value = "/signin", method=RequestMethod.POST)
	public String signInSubmit(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
		securityService.autologin(username, password);

		return "index";
	}

	/* Edit Account Details */
	@RequestMapping(value = {"/account", "/editAccount"}, method=RequestMethod.GET)
	public String editAccountPage(Model model) {
		/* Get current user profile */
		String username = securityService.findLoggedInUsername();
		User currentUser = mainService.findUserByUsername(username);

		/* Pass the class to the view */
		model.addAttribute("userForm", currentUser);

		return "account";
	}

	@RequestMapping(value = {"/account", "/editAccount"}, method=RequestMethod.POST)
	public String editAccountSubmit(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		/* Validates Form Submitted */
		validationService.validate(userForm, bindingResult);
	
		/* If error, redirect to sign up again */
		if (bindingResult.hasError())
			return "account";

		/* Else, update account to the database */
		mainService.saveUser(userForm);

		/* Keep user logged in after registering */
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "account";		
	}

	/* View All Transactions */
	@RequestMapping(value = "/purchases", method=RequestMethod.GET)
	public String viewAllTransactions(Model model) {
		/* Get current user */
		String username = securityService.findLoggedInUsername();
		User currUser = mainService.findUserByUsername(username);
	
		/* Find all transactions based on user */
		List<Transaction> allTransactions = mainService.findTransactionsByName(currUser);
		model.addAttribute("purchases", allTransactions);

		return "purchases";
	}

	/*** SUBSET: ADMIN ACTIONS ***/

	/* Admin Home Page */
	@RequestMapping(value = {"/admin", "/admin-prods"}, method=RequestMethod.GET)
	public String admin(Model model) {
		model.addAttribute("allProducts", mainService.findAllProducts());
	
		return "admin";
	}

	/* Create New Admin */
	@RequestMapping(value = "/admin-signup", method=RequestMethod.GET)
	public String adminSignupPage(Model model) {
		model.addAttribute("adminForm", new User());

		return "admin-signup";
	}

	@RequestMapping(value = "/admin-signup", method=RequestMethod.POST)
	public String adminSignupSubmit(@ModelAttribute("adminForm") User adminForm, BindingResult bindingResult, Model model) {
		/* Validate Form */
		validationService.validate(adminForm, bindingResult);

		/* If Error, redirect to signup page again */
		if (bindingResult.hasErrors()) {
			return "admin-signup";
		}
		/* Else. save new Admin account to the database */
		List<Role> roles = new List<Role>();
		roles.add(mainService.findRoleByName("ROLE_USER"));
		roles.add(mainService.findRoleByName("ROLE_ADMIN"));
		mainService.saveUser(adminForm, roles);

		return "admin";
	}

	/* View Transactions for Overriding */
	@RequestMapping(value = "/admin-trans", method=RequestMethod.GET)
	public String adminTransactions(Model model) {
		model.addAttribute("transactions", mainService.findAllTransactions());
	
		return "admin-trans";
	}

	/* View Users */
	@RequestMapping(value = "/admin-users", method=RequestMethod.GET)
	public String adminUsers(Model model) {
		model.addAttribute("users", mainService.findAllUsers());

		return "admin-users";
	}

	/* View Audit Trail */
	@RequestMapping(value = "/admin-audit-trail", method=RequestMethod.GET)
	public String adminAuditTrail(Model model) {
		return null;
	}

	/***** PRODUCT ACTIONS *****/

	/*** SUBSET: OTHER FUNCTIONS ***/

	/* Generate Product Brands */
	public List<String> generateProductTypes() {
		List<String> productTypes = new List<String>();
        productTypes.add("Desktop");
        productTypes.add("Laptop");
        productTypes.add("Tablet");
        productTypes.add("Mobile");

        return productTypes;
	}

	/* Generate Product Brands */
	public List<String> generateProductBrands() {
		List<String> productBrands = new List<String>();
		productBrands.add("Acer");
		productBrands.add("Asus");
		productBrands.add("Lenovo");
		productBrands.add("Samsung");

		return productBrands;
	}

	/*** SUBSET: MAIN FUNCTIONS ***/

	/* Add a Product */
	@RequestMapping(value = "/add-product", method=RequestMethod.GET)
	public String addProductPage(Model model) {
        model.addAttribute("prodForm", new Product());
        model.addAttribute("prodTypes", generateProductTypes());
        model.addAttribute("prodBrands", generateProductBrands());

        return "add-product";
	}

	@RequestMapping(value = "/add-product", method=RequestMethod.POST)
	public String addProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult, Model model) {
		/* Validate form submitted */
		validationService.validateProduct(prodForm, bindingResult);

		/* If error, create product again */
		if (bindingResult.hasErrors())
			return addProductPage(model);	// Call na lang ulit the function above

		/* Else, save new product to the database */
		mainService.saveProduct(prodForm);

		return admin(model);
	}

	/* Edit Product details */
	@RequestMapping(value = "/edit-product", method=RequestMethod.GET)
	public String editProductPage(Model model, @RequestParam("prodId") long prodId) {
		Product currProd = mainService.findProductByProductId(prodId);
		currProd.setProductId(prodId);

		model.addAttribute("prodForm", currProd);
		model.addAttribute("prodTypes", generateProductTypes());
		model.addAttribute("prodBrands", generateProductBrands());

		return "edit-product";
	}

	@RequestMapping(value = "/edit-product", method=RequestMethod.POST)
	public String editProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult, Model model) {
		/* Validate form submitted */
		validationService.validateProduct(prodForm, bindingResult);

		/* If error, create product again */
		if (bindingResult.hasErrors())
			return editProductPage(model, prodForm.getProductId());	// Call na lang ulit the function above

		mainService.saveProduct(prodForm);

		return admin(model);
	}

	/* Search Product */
	// Later na ito

	/* Filter Products */
	@RequestMapping(value = "/desktops", method=RequestMethod.GET)
	public String getDesktops(Model model) {
		model.addAttribute("allProducts", mainService.findProductsByType("Desktop"));

		return "index";
	}

	@RequestMapping(value = "/laptops", method=RequestMethod.GET)
	public String getLaptops(Model model) {
		model.addAttribute("allProducts", mainService.findProductsByType("Laptop"));
	
		return "index";
	}

	@RequestMapping(value = "/tablets", method=RequestMethod.GET)
	public String getTablets(Model model) {
		model.addAttribute("allProducts", mainService.findProductsByType("Tablet"));
	
		return "index";
	}

	@RequestMapping(value = "/mobiles", method=RequestMethod.GET)
	public String getMobiles(Model model) {
		model.addAttribute("allProducts", mainService.findProductsByType("Mobile"));
	
		return "index";
	}

	/* View a Product */
	@RequestMapping(value = "/view-product", method=RequestMethod.GET)
	public String viewProduct(Model model, @RequestParam("prodId") long prodId) {
		Product p = mainService.findProductByProductId(prodId);
		model.addAttribute("specificProd", p);

		return "view-product";
	}

	/* Delete a Product */
	@RequestMapping(value = "/delete-product", method=RequestMethod.GET)
	public String deleteProduct(Model model, @RequestParam("prodId") long prodId) {
		Product p = mainService.findProductByProductId(prodId);
		mainService.deleteProduct(p);

		return admin(model);
	}

	/***** TRANSACTION ACTIONS *****/

	/* Add Transaction/Buy Product */
	@RequestMapping(value="/buy-product", method=RequestMethod.POST)
	public String confirmPurchase(Model model, @RequestParam("prodId") long prodId, @RequestParam("prodQty") int prodQty) {
		Product p = mainService.findProductByProductId(prodId);
		User u = mainService.findUserByUsername(securityService.findLoggedInUsername());

		model.addAttribute("specificProd", p);
		model.addAttribute("prodQty", prodQty);
		model.addAttribute("currUser", u);

		return "confirm";
	}

	@RequestMapping(value = "/thank-you", method=RequestMethod.POST)
	public String buyProductSubmit(Model model, @ModelAttribute("prodId") long prodId, @RequestParam("prodQty") int quantity) {
		/* Retrieve chosen product and logged in user */
		Product p = mainService.findProductByProductId(prodId);
		User u = mainService.findUserByUsername(securityService.findLoggedInUsername());

		/* Create Transaction object */
		Transaction t = new Transaction();
		t.setUser(c);
		t.setProduct(p);
		t.setQuantity(quantity);
		t.setTotalAmount(p.getProductPrice() * quantity);
		t.setTimestamp(Calendar.getInstance());
		t.setStatus("Shipping");

		/* Save Transaction to the database */
		mainService.saveTransaction(t);

		/* Update Products' Quantity */
		p.setProductQuantity(p.getProductQuantity() - quantity);
		mainService.saveProduct(p);

		/* Thank you page */
		return "thank-you";
	}
}