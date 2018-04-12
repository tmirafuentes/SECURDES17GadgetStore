package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	/* All Services and Repositories */
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	/* Default Homepage */
	@RequestMapping(value = {"/", "/welcome", "/index"}, method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("allProducts", productService.findAllProducts());
		return "user/index";
	}

	/* Admin Home Page */
	@RequestMapping(value = {"/admin", "/admin/products"}, method=RequestMethod.GET)
	public String adminHomePage(Model model) {
		/* Load all products at Admin Home Page */
		model.addAttribute("allProducts", productService.findAllProducts());
		return "admin/admin";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String errorPage(Model model) {
		return "error";
	}
}