package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
public class MainController {
	/* All Services and Repositories */
	@Autowired
	private MainService mainService;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private SecurityService securityService;

	/* Default Homepage */
	@RequestMapping(value = {"/", "/welcome", "/index"}, method=RequestMethod.GET)
	public ModelAndView index(Model model) {
		return new ModelAndView("index", "allProducts", mainService.findAllProducts());
	}

	/***** USER ACTIONS *****/

	/* Registration of Account */
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public ModelAndView signUpPage(Model model) {
		return new ModelAndView("signup", "userForm", new UserDto());
	}

	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public String signUpSubmit(@ModelAttribute("userForm") @Valid UserDto userForm, BindingResult bindingResult, Model model, Request request) {
		/* Validates Form Submitted*/
		validationService.validate(userForm, bindingResult);
	
		/* If error, redirect to sign up again */
		if (bindingResult.hasErrors())
			return "signup";

		/* Else, save new account to the database */
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(mainService.findRoleByName("ROLE_USER"));
		User newUser = mainService.saveUser(userForm, roles);

		/* Keep user logged in after registering */
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/";
	}

	/* Sign In */
	@RequestMapping(value = "/signin", method=RequestMethod.GET)
	public ModelAndView signInPage(Model model, String error, String logout) {
		if (error != null)
			return new ModelAndView("signin", "error", "Either your username or password is invalid. Please try again.");

		if (logout != null)
			return new ModelAndView("signin", "message", "You have logged out successfully.");

		return new ModelAndView("signin", null, null);
	}

	@RequestMapping(value = "/signin", method=RequestMethod.POST)
	public String signInSubmit(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
		securityService.autologin(username, password);

		return "redirect:/";
	}

	/* Edit Account Details */
	@RequestMapping(value = "/account", method=RequestMethod.GET)
	public ModelAndView editAccountPage(Model model) {
		/* Get current user profile */
		String username = securityService.findLoggedInUsername();
		User currentUser = mainService.findUserByUsername(username);

		/* Pass the class to the view */
		return new ModelAndView("account", "userForm", currentUser);
	}

	@RequestMapping(value = "/account", method=RequestMethod.POST)
	public ModelAndView editAccountSubmit(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		/* Validates Form Submitted */
		validationService.validate(userForm, bindingResult);
	
		/* If error, redirect to edit account again */
		if (bindingResult.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("error", "There is an error updating your account. Please try again.");
			model.put("userForm", userForm);
			return new ModelAndView("account", model);
		}

		/* Else, update account to the database */
		mainService.saveUser(userForm);

		return editAccountPage(model);		
	}

	/* View All Transactions */
	@RequestMapping(value = "/purchases", method=RequestMethod.GET)
	public String viewAllTransactions(Model model) {
		/* Get current user */
		String username = securityService.findLoggedInUsername();
		User currUser = mainService.findUserByUsername(username);
	
		/* Find all transactions based on user */
		ArrayList<Transaction> allTransactions = mainService.findTransactionsByUser(currUser);
		
		return new ModelAndView("purchases", "purchases", allTransactions);
	}

	/*** SUBSET: ADMIN ACTIONS ***/

	/* Admin Home Page */
	@RequestMapping(value = "/admin", method=RequestMethod.GET)
	public ModelAndView admin(Model model) {
		/* Load all products at Admin Home Page */
		return new ModelAndView("admin", "allProducts", mainService.findAllProducts());
	}

	/* Create New Admin */
	@RequestMapping(value = "/admin-signup", method=RequestMethod.GET)
	public ModelAndView adminSignupPage(Model model) {
		/* Make a sign-up form for Admin */
		return new ModelAndView("admin-signup", "adminForm", new User());
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
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(mainService.findRoleByName("ROLE_USER"));
		roles.add(mainService.findRoleByName("ROLE_ADMIN"));
		mainService.saveUser(adminForm, roles);

		return "redirect:/admin";
	}

	/* View Transactions for Overriding */
	@RequestMapping(value = "/admin-trans", method=RequestMethod.GET)
	public ModelAndView adminTransactions(Model model) {
		return new ModelAndView("admin-trans", "transactions", mainService.findAllTransactions());
	}

	/* View Users */
	@RequestMapping(value = "/admin-users", method=RequestMethod.GET)
	public String adminUsers(Model model) {
		return new ModelAndView("admin-users", "users", mainService.findAllUsers());
	}

	/* View Audit Trail
	@RequestMapping(value = "/admin-audit-trail", method=RequestMethod.GET)
	public String adminAuditTrail(Model model) {
		return null;
	} */

	/***** PRODUCT ACTIONS *****/

	/*** SUBSET: OTHER FUNCTIONS ***/

	/* Generate Product Brands */
	public ArrayList<String> generateProductTypes() {
		ArrayList<String> productTypes = new ArrayList<String>();
        productTypes.add("Desktop");
        productTypes.add("Laptop");
        productTypes.add("Tablet");
        productTypes.add("Mobile");

        return productTypes;
	}

	/* Generate Product Brands */
	public ArrayList<String> generateProductBrands() {
		ArrayList<String> productBrands = new ArrayList<String>();
		productBrands.add("Acer");
		productBrands.add("Asus");
		productBrands.add("Lenovo");
		productBrands.add("Samsung");

		return productBrands;
	}

	/*** SUBSET: MAIN FUNCTIONS ***/

	/* Add a Product */
	@RequestMapping(value = "/add-product", method=RequestMethod.GET)
	public ModelAndView addProductPage(Model model) {
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("prodForm", new Product());
        model.put("prodTypes", generateProductTypes());
        model.put("prodBrands", generateProductBrands());

        return new ModelAndView("add-product", model);
	}

	@RequestMapping(value = "/add-product", method=RequestMethod.POST)
	public String addProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult, Model model) {
		/* Validate form submitted */
		//validationService.validateProduct(prodForm, bindingResult);

		/* If error, create product again */
		if (bindingResult.hasErrors())
			return addProductPage(model);	// Call na lang ulit the function above

		/* Else, save new product to the database */
		mainService.saveProduct(prodForm);

		return "redirect:/admin";
	}

	/* Edit Product details */
	@RequestMapping(value = "/edit-product", method=RequestMethod.GET)
	public String editProductPage(Model model, @RequestParam("prodId") Long prodId) {
		Product currProd = mainService.findProductByProductId(prodId);
		currProd.setProductId(prodId);

		Map<String, Object> model = new HashMap<String, Object>();
        model.put("prodForm", currProd);
        model.put("prodTypes", generateProductTypes());
        model.put("prodBrands", generateProductBrands());

        return new ModelAndView("edit-product", model);
	}

	@RequestMapping(value = "/edit-product", method=RequestMethod.POST)
	public String editProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult, Model model) {
		/* Validate form submitted */
		//validationService.validateProduct(prodForm, bindingResult);

		/* If error, create product again */
		if (bindingResult.hasErrors())
			return editProductPage(model, prodForm.getProductId());	// Call na lang ulit the function above

		mainService.saveProduct(prodForm);

		return "redirect:/admin";
	}

	/* Search Product */
	// Later na ito

	/* Filter Products */
	@RequestMapping(value = "/desktops", method=RequestMethod.GET)
	public ModelAndView getDesktops(Model model) {
		return new ModelAndView("index", "allProducts", mainService.findProductsByType("Desktop"));
	}

	@RequestMapping(value = "/laptops", method=RequestMethod.GET)
	public ModelAndView getLaptops(Model model) {
		return new ModelAndView("index", "allProducts", mainService.findProductsByType("Laptop"));
	}

	@RequestMapping(value = "/tablets", method=RequestMethod.GET)
	public ModelAndView getTablets(Model model) {
		return new ModelAndView("index", "allProducts", mainService.findProductsByType("Tablet"));
	}

	@RequestMapping(value = "/mobiles", method=RequestMethod.GET)
	public ModelAndView getMobiles(Model model) {
		return new ModelAndView("index", "allProducts", mainService.findProductsByType("Mobile"));
	}

	/* View a Product */
	@RequestMapping(value = "/view-product", method=RequestMethod.GET)
	public ModelAndView viewProduct(Model model, @RequestParam("prodId") long prodId) {
		Product p = mainService.findProductByProductId(prodId);

		return new ModelAndView("view-product", "specificProd", p);
	}

	/* Delete a Product */
	@RequestMapping(value = "/delete-product", method=RequestMethod.GET)
	public String deleteProduct(Model model, @RequestParam("prodId") long prodId) {
		Product p = mainService.findProductByProductId(prodId);
		mainService.deleteProduct(p);

		return "redirect:/admin";
	}

	/***** TRANSACTION ACTIONS *****/

	/* Add Transaction/Buy Product */
	@RequestMapping(value="/buy-product", method=RequestMethod.POST)
	public ModelAndView confirmPurchase(Model model, @RequestParam("prodId") long prodId, @RequestParam("prodQty") int prodQty) {
		Product p = mainService.findProductByProductId(prodId);
		User u = mainService.findUserByUsername(securityService.findLoggedInUsername());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("specificProd", p);
		model.put("prodQty", prodQty);
		model.put("currUser", u);

		return new ModelAndView("confirm", model);
	}

	@RequestMapping(value = "/thank-you", method=RequestMethod.POST)
	public String buyProductSubmit(Model model, @ModelAttribute("prodId") long prodId, @RequestParam("prodQty") int quantity) {
		/* Retrieve chosen product and logged in user */
		Product p = mainService.findProductByProductId(prodId);
		User u = mainService.findUserByUsername(securityService.findLoggedInUsername());

		/* Create Transaction object */
		Transaction t = new Transaction();
		t.setUser(u);
		t.setProduct(p);
		t.setQuantity(quantity);
		t.setTotalAmount(p.getProductPrice() * quantity);
		t.setTimestamp(Calendar.getInstance());
		t.setStatus(true);

		/* Save Transaction to the database */
		mainService.saveTransaction(t);

		/* Update Products' Quantity */
		p.setProductQuantity(p.getProductQuantity() - quantity);
		mainService.saveProduct(p);

		/* Thank you page */
		return "thank-you";
	}
}