package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.security.registration.OnRegistrationCompleteEvent;
import edu.dlsu.securdeproject.security.registration.UserDto;
import edu.dlsu.securdeproject.security.SecurityService;
import edu.dlsu.securdeproject.security.registration.VerificationToken;
import edu.dlsu.securdeproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class MainController {
	/* All Services and Repositories */
	@Autowired
	private MainService mainService;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	@Autowired
	private MessageSource messages;

	/* Default Homepage */
	@RequestMapping(value = {"/", "/welcome", "/index"}, method=RequestMethod.GET)
	public ModelAndView index(Model model) {
		if (mainService.findRoleByName("ROLE_USER") == null)
			mainService.saveRole(new Role("ROLE_USER"));
		if (mainService.findRoleByName("ROLE_ADMIN") == null)
			mainService.saveRole(new Role("ROLE_ADMIN"));

		return new ModelAndView("index", "allProducts", mainService.findAllProducts());
	}

	/***** USER ACTIONS *****/

	/* Registration of Account */
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public ModelAndView signUpPage(Model model) {
		return new ModelAndView("signup", "userForm", new UserDto());
	}

	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public ModelAndView signUpSubmit(@ModelAttribute("userForm") @Valid UserDto userForm, BindingResult bindingResult,
									 Model model, WebRequest request) {
		/* Validates Form Submitted*/
		validationService.validate(userForm, bindingResult);
	
		/* If error, redirect to sign up again */
		if (bindingResult.hasErrors()) {
            return new ModelAndView("signup", "userForm", userForm);
        }

		/* Else, save new account to the database */
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(mainService.findRoleByName("ROLE_USER"));
		User newUser = mainService.saveUser(userForm, roles);

		/* Send E-mail verification to confirm */
		try {
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newUser, request.getLocale(), appUrl));
		} catch(Exception e) {System.out.println("Error: " + e);}

		///* Keep user logged in after registering */
		//securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return new ModelAndView("signup-success", "user", userForm);
	}

	@RequestMapping(value = "/signup-confirm", method=RequestMethod.GET)
	public ModelAndView confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
		Locale locale = request.getLocale();

		// Check if token is valid
		VerificationToken verificationToken = mainService.getVerificationToken(token);
		if (verificationToken == null) {
			String message = messages.getMessage("message.invalidToken", null, locale);
			return new ModelAndView("error", "message", message);
		}

		// Check if token has expired
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			String message = messages.getMessage("message.expiredToken", null, locale);
			return new ModelAndView("error", "message", message);
		}

		// Enable user and auto login
		user.setEnabled(true);
		mainService.saveUser(user);
		securityService.autologin(user.getUsername(), user.getPassword());
		return new ModelAndView("welcome", "allProducts", mainService.findAllProducts());
	}

	/* Sign In */
	@RequestMapping(value = "/signin", method=RequestMethod.GET)
	public ModelAndView signInPage(Model model, String error, String logout) {
		if (error != null)
			return new ModelAndView("signin", "error", "Either your username or password is invalid. Please try again.");

		if (logout != null)
			return new ModelAndView("signin", "message", "You have logged out successfully.");

		return new ModelAndView("signin", "message", null);
	}

	@RequestMapping(value = "/signin", method=RequestMethod.POST)
	public String signInSubmit(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
		securityService.autologin(username, password);

		/* Redirect to Admin home page if Admin */
		User currUser = mainService.findUserByUsername(securityService.findLoggedInUsername());
		for(Role role : currUser.getRoles())
			if (role.getName().equals("ROLE_ADMIN"))
				return "redirect:/admin";

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
			Map<String, Object> models = new HashMap<String, Object>();
			models.put("error", "There is an error updating your account. Please try again.");
			models.put("userForm", userForm);
			return new ModelAndView("account", models);
		}

		/* Else, update account to the database */
		mainService.saveUser(userForm);

		return editAccountPage(model);		
	}

	/* Forgot Password */
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(HttpServletRequest request, @RequestParam("email") String email) {
		User user = mainService.findUserByEmail(email);
		if (user == null)
			return "forgot-password";

		String token = UUID.randomUUID().toString();
		mainService.createPasswordResetToken(user, token);
		mainService.sendResetTokenEmail("http://" + request.getServerName() + ":" +
										request.getServerPort() + request.getContextPath(), request.getLocale(),
										token, user);

		return "redirect:/forgot-password-confirm";
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public String changePasswordPage(Locale locale, Model model, @RequestParam("id") Long id, @RequestParam("token") String token) {
		String result = mainService.validatePasswordResetToken(id, token);
		if (result != null) {
			model.addAttribute("message", messages.getMessage("message." + result, null, locale));
			return "redirect:/login";
		}
		return "/change-password";
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public String changePasswordSubmit(@RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm) {
		/* Validate Passwords */
		if (!validationService.validatePassword(password, passwordConfirm))
			return "/change-password";

		/* Save New Password */
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mainService.saveNewPassword(user, password);
		return "redirect:/change-password-success";
	}

	/* View All Transactions */
	@RequestMapping(value = "/purchases", method=RequestMethod.GET)
	public ModelAndView viewAllTransactions(Model model) {
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
	public String adminSignupSubmit(@ModelAttribute("adminForm") UserDto adminForm, BindingResult bindingResult, Model model) {
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
	public ModelAndView adminUsers(Model model) {
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
		Map<String, Object> models = new HashMap<String, Object>();
        models.put("prodForm", new Product());
        models.put("prodTypes", generateProductTypes());
        models.put("prodBrands", generateProductBrands());

        return new ModelAndView("add-product", models);
	}

	@RequestMapping(value = "/add-product", method=RequestMethod.POST)
	public String addProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult, Model model) {
		/* Validate form submitted */
		//validationService.validateProduct(prodForm, bindingResult);

		/* If error, create product again */
		if (bindingResult.hasErrors())
			return "add-product";	// Call na lang ulit the function above

		/* Else, save new product to the database */
		mainService.saveProduct(prodForm);

		return "redirect:/admin";
	}

	/* Edit Product details */
	@RequestMapping(value = "/edit-product", method=RequestMethod.GET)
	public ModelAndView editProductPage(Model model, @RequestParam("prodId") Long prodId) {
		Product currProd = mainService.findProductByProductId(prodId);
		currProd.setProductId(prodId);

		Map<String, Object> models = new HashMap<String, Object>();
        models.put("prodForm", currProd);
        models.put("prodTypes", generateProductTypes());
        models.put("prodBrands", generateProductBrands());

        return new ModelAndView("edit-product", models);
	}

	@RequestMapping(value = "/edit-product", method=RequestMethod.POST)
	public String editProductSubmit(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult, Model model) {
		/* Validate form submitted */
		//validationService.validateProduct(prodForm, bindingResult);

		/* If error, create product again */
		if (bindingResult.hasErrors())
			return "edit-product";	// Call na lang ulit the function above

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

		Map<String, Object> models = new HashMap<String, Object>();
		models.put("specificProd", p);
		models.put("prodQty", prodQty);
		models.put("currUser", u);

		return new ModelAndView("confirm", models);
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