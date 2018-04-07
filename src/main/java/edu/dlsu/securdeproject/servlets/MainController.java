package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.security.SecurityService;
import edu.dlsu.securdeproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

	/* Admin Home Page */
	@RequestMapping(value = {"/admin", "/admin/products"}, method=RequestMethod.GET)
	public ModelAndView admin(Model model) {
		/* Load all products at Admin Home Page */
		return new ModelAndView("admin", "allProducts", mainService.findAllProducts());
	}

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