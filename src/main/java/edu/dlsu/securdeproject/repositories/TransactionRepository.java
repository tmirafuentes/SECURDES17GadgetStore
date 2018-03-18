package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.classes.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
<<<<<<< HEAD
    Transaction findByTransactionId(Long transactionId);
    List<Transaction> findAllByUser(User user);
=======
    ArrayList<Transaction> findAllByUser(User user);

    Transaction findByTransactionId(Long transId);
>>>>>>> 2c1a7ba45731d0616b5c259d9316b3e6190ec6f5
}