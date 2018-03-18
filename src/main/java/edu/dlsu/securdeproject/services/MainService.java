package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Role;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import edu.dlsu.securdeproject.repositories.RoleRepository;
import edu.dlsu.securdeproject.repositories.TransactionRepository;
import edu.dlsu.securdeproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

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

	/* Encryptor */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/***** USER SERVICES *****/

	/*** DATABASE SERVICES ***/

	/* Save New User */
	public void saveUser(User u, ArrayList<Role> roles) {
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		u.setRoles(new HashSet<>(roles));
		userRepository.save(u);
	}

	/* Update User */
	public void saveUser(User u) {
		userRepository.save(u);
	}

	/* Retrieve specific user by username */
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/* Retrieve all users */
	public ArrayList<User> findAllUsers() {
		return (ArrayList<User>) userRepository.findAll();
	}

	public Role findRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	/*** AUTHENTICATION AND AUTHORIZATION SERVICES ***/
	
	/* Retrieve User for details */

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