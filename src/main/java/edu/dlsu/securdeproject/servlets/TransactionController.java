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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
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

        return "transactions";
    }

    /*** Add Transaction/Buy Product ***/
    /*** URL Maps to here from View Product page ***/
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buyProductSubmit(Model model, @RequestParam("prodId") long prodId, @RequestParam("prodQty") int prodQty)
    {
        /* Retrieve Product and User Details */
        Product p = productService.findProductByProductId(prodId);
        User u = userService.findUserByUsername(securityService.findLoggedInUsername());

        model.addAttribute("product", p);
        model.addAttribute("quantity", prodQty);
        model.addAttribute("user", u);
        return "buy-product";
    }

    /*** Confirm Purchase of Product ***/
    @RequestMapping(value = "/confirm-purchase", method = RequestMethod.POST)
    public String confirmPurchaseSubmit(Model model, @ModelAttribute("product") Product product, @ModelAttribute("quantity") int quantity,
                                        @ModelAttribute("user") User user, @RequestParam("password") String password)
    {
        /* Re-authenticate */
        if (!securityService.authenticateAccount(user.getUsername(), securityService.encryptPassword(password))) {
            model.addAttribute("message", messages.getMessage("message.badCredentials",null, null));
            return "buy-product";
        }

        /* Update Product Quantity */
        productService.updateProductQuantity(product, quantity);

        /* Create Transaction Object */
        transactionService.saveTransaction(user, product, quantity);

        return "redirect:/thank-you";
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
