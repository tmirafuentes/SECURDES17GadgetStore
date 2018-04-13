package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.security.SecurityService;
import edu.dlsu.securdeproject.services.ProductService;
import edu.dlsu.securdeproject.services.TransactionService;
import edu.dlsu.securdeproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@SessionAttributes({"product", "quantity", "user", "total"})
public class TransactionController {
    /* Services */
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserDetailsService userDetailsService;

    /* Extra Stuff */
    private MessageSource messages;

    /***
     ***
        URL MAPPING
     ***
     ***/

    /*** View All Transactions ***/
    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public String viewTransactionsPage(Model model)
    {
        /* Get Current User */
        User currUser = retrieveUser();

        /* Get All Transactions from User */
        ArrayList<Transaction> allTransactions = transactionService.findTransactionsByUser(currUser);
        model.addAttribute("purchases", allTransactions);

        return "user/purchases";
    }

    /*** Add Transaction/Buy Product ***/
    /*** URL Maps to here from View Product page ***/
    @RequestMapping(value = "/view-product", method = RequestMethod.POST)
    public String buyProductSubmit(Model model, @ModelAttribute("product") Product product,
                                   @RequestParam("prodQty") int prodQty, final RedirectAttributes redirectAttributes)
    {
        /* Retrieve Product and User Details */
        //System.out.println("Username: " + securityService.findLoggedInUsername());
        User u = userService.findUserByUsername(securityService.findLoggedInUsername());

        redirectAttributes.addFlashAttribute("quantity", prodQty);
        redirectAttributes.addFlashAttribute("user", u);
        redirectAttributes.addFlashAttribute("product", product);
        redirectAttributes.addFlashAttribute("total", product.getProductPrice() * prodQty);

        return "redirect:/buy-product";
    }

    @RequestMapping(value = "/buy-product", method = RequestMethod.GET)
    public String buyProductPage(Model model, @ModelAttribute("product") final Product product,
                                 @ModelAttribute("user") User user, @ModelAttribute("quantity") int quantity,
                                 @ModelAttribute("total") double total)
    {
        model.addAttribute("quantity", quantity);
        model.addAttribute("user", user);
        model.addAttribute("product", product);
        model.addAttribute("total", total);

        return "user/buy-product";
    }

    /*** Confirm Purchase of Product ***/
    @RequestMapping(value = "/buy-product", method = RequestMethod.POST)
    public String confirmPurchaseSubmit(Model model, @ModelAttribute("product") Product product, @ModelAttribute("quantity") int quantity,
                                        @RequestParam("password") String password)
    {
        User user = retrieveUser();
        //userDetailsService.loadUserByUsername(user.getUsername());

        /* Re-authenticate */
        if (!securityService.authenticateAccount(user.getUsername(), password)) {
            //model.addAttribute("message", messages.getMessage("message.badCredentials",null, null));
            return "user/buy-product";
        }

        /* Update Product Quantity */
        productService.updateProductQuantity(product, quantity);

        /* Create Transaction Object */
        transactionService.saveTransaction(user, product, quantity);

        return "redirect:/thank-you";
    }

    @RequestMapping(value = "/thank-you", method = RequestMethod.GET)
    public String thankYouPage(Model model) {
        return "product/thank-you";
    }

    @RequestMapping(value = "/admin/override", method = RequestMethod.GET)
    public String overridePurchase(Model model, @RequestParam("p") String link) {
        Transaction t = transactionService.findTransactionByLink(link);
        transactionService.overridePurchase(t);

        return "redirect:/admin/trans";
    }

    /***
     ***
     OTHER FUNCTIONS
     ***
     ***/

    /*** Retrieve current user ***/
    private User retrieveUser()
    {
        String username = securityService.findLoggedInUsername();
        return userService.findUserByUsername(username);
    }
}
