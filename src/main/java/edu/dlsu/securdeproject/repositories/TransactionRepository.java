package edu.dlsu.securdeproject.repositories;

import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.classes.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    ArrayList<Transaction> findAllByUser(User user);

    Transaction findById(Long transId);
}