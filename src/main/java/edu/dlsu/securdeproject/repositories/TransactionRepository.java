package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Transaction findByTransactionId(long transactionId);
    List<Transaction> findAllByCustomer(Customer customer);
}