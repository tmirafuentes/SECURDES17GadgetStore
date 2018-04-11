package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.*;
import edu.dlsu.securdeproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@Component
public class DataService implements ApplicationRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public void run(ApplicationArguments args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        /* Initialize Admin */
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(userService.findRoleByName("ROLE_USER"));
        roles.add(userService.findRoleByName("ROLE_ADMIN"));

        User admin = new User();
        admin.setUsername("Admin");
        admin.setEmail("mark_gavin_sanchez@dlsu.edu.ph");
        admin.setRoles(new HashSet<>(roles));
        admin.setEnabled(true);
        userService.saveUser(admin);
        userService.saveNewPassword(admin, "password123");

        /* Additional Dummy Accounts */
        ArrayList<Role> role_user = new ArrayList<Role>();
        role_user.add(userService.findRoleByName("ROLE_USER"));
        
        User user1 = new User();
        user1.setUsername("animo");
        user1.setFirstName("Animo");
        user1.setLastName("La Salle");
        user1.setEmail("animolozol@gmail.com");
        user1.setRoles(new HashSet<>(role_user));
        user1.setEnabled(true);
        userService.saveUser(user1);
        userService.saveNewPassword(user1, "lozol");
        
        User user2 = new User();
        user2.setUsername("jolenevalenzuela");
        user2.setEmail("jolenevalenzuela@rocketmail.com");
        user2.setRoles(new HashSet<>(role_user));
        user2.setEnabled(true);
        userService.saveUser(user2);
        userService.saveNewPassword(user2, "3n3l0j");

        User user3 = new User();
        user3.setUsername("cardo_dalisay");
        user3.setEmail("probinsyano@mail.com");
        user3.setRoles(new HashSet<>(role_user));
        user3.setEnabled(true);
        userService.saveUser(user3);
        userService.saveNewPassword(user3, "sup3rcard0!");

        User user4 = new User();
        user4.setUsername("tmirafuentes");
        user4.setLastName("Mirafuentes");
        user4.setFirstName("Troy");
        user4.setEmail("troy_mirafuentes@dlsu.edu.ph");
        user4.setMobileNumber("09123456789");
        user4.setMailAddress("Manila City");
        try {
            user4.setBirthdate(dateFormat.parse("05-12-2014"));
        } catch (Exception e) {}
        user4.setEnabled(true);
        user4.setRoles(new HashSet<>(role_user));
        userService.saveUser(user4);
        userService.saveNewPassword(user4, "R4inmaker;");

        Product p1 = new Product();
        p1.setProductName("Acer Aspire XC-730");
        p1.setProductPrice(17988);
        p1.setProductQuantity(100);
        p1.setProductDescription("Intel Celeron J3455 Quad Core up to 2.3 GHz / 2GB / 500GB / Intel HD Graphics / 18.5-in Acer EB192 monitor / Windows 10");
        p1.setProductBrand(productService.findBrandByName("Acer"));
        p1.setProductType(productService.findTypeByName("Desktop"));
        productService.saveProduct(p1);

        Product p2 = new Product();
        p2.setProductName("HP Pavilion X360 14-BA076TU (Silver)");
        p2.setProductPrice(35988);
        p2.setProductQuantity(200);
        p2.setProductDescription("Intel Core i3-7130U 2.70 GHz Processor (3M Cache, 2.70 GHz) / 4GB / 1TB / NO ODD / 14-in diagonal HD SVA WLED-backlit multitouch-enabled edge-to-edge glass (1366 x 768) / Intel HD Graphics 620 / Windows 10 / with 15.6 Value Backpack");
        p2.setProductBrand(productService.findBrandByName("HP"));
        p2.setProductType(productService.findTypeByName("Laptop"));
        productService.saveProduct(p2);

        Product p3 = new Product();
        p3.setProductName("Apple iPad Mini 3 16GB Space Gray with Touch ID");
        p3.setProductPrice(17999);
        p3.setProductQuantity(120);
        p3.setProductDescription("16GB Storage / 7.9-inch Retina Display / 5MP & 1.2MP Camera / Dual-core 1.3 GHz Cyclone (ARM v8-based) / PowerVR G6430 (quad-core graphics) / iOS 8.1");
        p3.setProductBrand(productService.findBrandByName("Apple"));
        p3.setProductType(productService.findTypeByName("Tablet"));
        productService.saveProduct(p3);

        Product p4 = new Product();
        p4.setProductName("Samsung Galaxy J1 Mini");
        p4.setProductPrice(3990);
        p4.setProductQuantity(50);
        p4.setProductDescription("4-inch TFT capacitive touchscreen / Spreadtrum SC8830 Quad-core 1.2 GHz / 756MB / 8GB/ 5 MP, LED flash, Secondary VGA / Android OS, v5.1 (Lollipop)");
        p4.setProductBrand(productService.findBrandByName("Samsung"));
        p4.setProductType(productService.findTypeByName("Mobile"));
        productService.saveProduct(p4);
    }
}
