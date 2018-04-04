package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.repositories.*;
import edu.dlsu.securdeproject.security.password_recovery.PasswordResetToken;
import edu.dlsu.securdeproject.security.registration.UserDto;
import edu.dlsu.securdeproject.security.registration.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MainService {
	/* Repositories */
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private PasswordTokenRepository passwordTokenRepository;

	/* Mail Sender */
	private JavaMailSender mailSender;

	/* Encryptor */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MessageSource messages;

	/***** USER SERVICES *****/

	/*** DATABASE SERVICES ***/

	/* Save New User */
	public User saveUser(UserDto u, ArrayList<Role> roles) {
		/* Transfer to User object */
		User user = new User();
		user.setBirthdate(u.getBirthdate());
		user.setEmail(u.getEmail());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setMailAddress(u.getMailAddress());
		user.setMobileNumber(u.getMobileNumber());
		user.setUsername(u.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		user.setRoles(new HashSet<>(roles));
		userRepository.save(user);

		return user;
	}

	/* Update User */
	public void saveUser(User u) {
		userRepository.save(u);
	}

	/* Update Password */
	public void saveNewPassword(User user, String password) {
		user.setPassword(bCryptPasswordEncoder.encode(password));
		userRepository.save(user);
	}

	/* Retrieve specific user by username */
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/* Retrieve specific user by email */
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/* Retrieve all users */
	public ArrayList<User> findAllUsers() {
		return (ArrayList<User>) userRepository.findAll();
	}

	/* Add role */
	public void saveRole(Role r) { roleRepository.save(r);}

	/* Retrieve role name */
	public Role findRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	/*** AUTHENTICATION AND AUTHORIZATION SERVICES ***/
	
	/* Forgotten Password Token Creation */
	public void createPasswordResetToken(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(user, token);
		passwordTokenRepository.save(myToken);
	}

	/* Retrieve Password Token */
	public PasswordResetToken getPasswordToken(String token) {
		return passwordTokenRepository.findByToken(token);
	}

	/* Send Forgot Password E-mail Token */
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}

	/* Validate Password Token */
	public String validatePasswordResetToken(Long id, String token) {
		/* Invalid Token */
		PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
		if ((passToken == null) || (passToken.getUser().getUserId() != id))
			return "invalidToken";

		/* Expired Token */
		Calendar cal = Calendar.getInstance();
		if((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0)
			return "expiredToken";

		User user = passToken.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}

	/* E-mail Verification Token Creation */
	public void createEmailVerificationToken(User user, String token) {
		VerificationToken newToken = new VerificationToken(user, token);
		verificationTokenRepository.save(newToken);
	}

	/* Update E-Mail Verification Token */
	public VerificationToken generateNewVerificationToken(String exisitingToken) {
		VerificationToken vToken = getVerificationToken(existingToken);
		vToken.setToken(UUID.randomUUID().toString());
		vToken = tokenRepository.save(vToken);
		return vToken;
	}

	/* Retrieve E-mail Verification Token */
	public VerificationToken getVerificationToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}

	/***** PRODUCT SERVICES *****/

	/*** DATABASE SERVICES ***/
	/* Save Product */
	public void saveProduct(Product p) {
		productRepository.save(p);
	}

	/* Retrieve specific product */
	public Product findProductByProductId(Long productId) {
		return (Product) productRepository.findByProductId(productId);
	}

	/* Retrieve all products */
	public ArrayList<Product> findAllProducts() {
		return (ArrayList<Product>) productRepository.findAll();
	}

	/* Retrieve products by specific type */
	public ArrayList<Product> findProductsByType(String productType) {
		return (ArrayList<Product>) productRepository.findAllByProductType(productType);
	} 

	/* Retrieve products by search */
	public ArrayList<Product> findProductsBySearch(String productName) {
		return (ArrayList<Product>) productRepository.findAllByProductNameContains(productName);
	}

	/* Delete a product */
	public void deleteProduct(Product p) {
		productRepository.delete(p);
	}

	/***** TRANSACTION SERVICES *****/

	/*** DATABASE SERVICES ***/

	/* Save Transaction */
	public void saveTransaction(Transaction t) {
		transactionRepository.save(t);
	}

	/* Retrieve specific transaction by ID */
	public Transaction findTransactionByTransactionId(Long transId) {
		return (Transaction) transactionRepository.findByTransactionId(transId);
	}

	/* Retrieve specific transaction by User */
	public ArrayList<Transaction> findTransactionsByUser(User u) {
		return (ArrayList<Transaction>) transactionRepository.findAllByUser(u);
	}

	/* Retrieve all transactions */
	public ArrayList<Transaction> findAllTransactions() {
		return (ArrayList<Transaction>) transactionRepository.findAll();
	}

	/* Delete a transaction */
	public void deleteTransaction(Transaction t) {
		transactionRepository.delete(t);
	}

}