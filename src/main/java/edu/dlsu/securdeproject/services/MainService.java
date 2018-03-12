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
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
public class MainService implements UserDetailsService {
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
	public User saveUser(User u, List<Role> roles) {
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		u.setRoles(new HashSet<>(roles));
		userRepository.save(u);

		return u;
	}

	/* Update User */
	public void saveUser(User u) {
		userRepository.save(u);
	}

	/* Retrieve specific user by username */
	public User findUserByUsername(String username) {
		userRepository.findByUsername(username);
	}

	/* Retrieve all users */
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	/* Retrieve role name */
	public Role findRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	/*** AUTHENTICATION AND AUTHORIZATION SERVICES ***/
	
	/* Retrieve User for details */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findUserByUsername(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles())
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
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
	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	/* Retrieve products by specific type */
	public List<Product> findProductsByType(String productType) {
		return (List<Product>) productRepository.findAllByProductType(productType);
	} 

	/* Retrieve products by search */
	public List<Product> findProductsBySearch(String productName) {
		return (List<Product>) productRepository.findAllByProductNameContains(productType);
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
	public List<Transaction> findTransactionsByUser(User u) {
		return (List<Transaction>) transactionRepository.findAllByUser(t);
	}

	/* Retrieve all transactions */
	public List<Transaction> findAllTransactions() {
		return List<Transaction> transactionRepository.findAll();
	}

	/* Delete a transaction */
	public void deleteTransaction(Transaction t) {
		transactionRepository.delete(t);
	}

}