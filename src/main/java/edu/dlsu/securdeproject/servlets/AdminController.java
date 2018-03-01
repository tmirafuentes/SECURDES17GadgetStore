package edu.dlsu.securdeproject.servlets;

import edu.dlsu.securdeproject.classes.Admin;
import edu.dlsu.securdeproject.repositories.AdminRepository;
import edu.dlsu.securdeproject.services.AdminService;
import edu.dlsu.securdeproject.services.SecurityService;
import edu.dlsu.securdeproject.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ValidationService validationService;

    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String adminHome(Model model) {
        return "adminHome";
    }

    @RequestMapping(value = "/adminTransac", method = RequestMethod.GET)
    public String adminViewTrans(Model model) {
        return "adminTransac";
    }

    @RequestMapping(value="/adminSignup", method= RequestMethod.GET)
    public String signUpPage(Model model) {
        model.addAttribute("adminForm", new Admin());

        return "adminSignup";
    }

    @RequestMapping(value="/adminSignup", method = RequestMethod.POST)
    public String signUpSubmit(@ModelAttribute("adminForm") Admin adminForm, BindingResult bindingResult, Model model) {
        //validationService.validate(adminForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "adminSignup";
        }

        /* Else, save new account to the database */
        adminForm.setPassword(bCryptPasswordEncoder.encode(adminForm.getPassword()));
        adminRepository.save(adminForm);

        /* Keep user logged in after registering */
        securityService.autologin(adminForm.getUsername(), adminForm.getPasswordConfirm());

        return "adminHome";
    }
}
