package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Product;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.classes.User;
import edu.dlsu.securdeproject.repositories.ProductRepository;
import edu.dlsu.securdeproject.repositories.TransactionRepository;
import edu.dlsu.securdeproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    /***
     ***
        DATABASE SERVICES
     ***
     ***/

    /* Save Transaction */
    public void saveTransaction(User u, Product p, int quantity) {
        Transaction t = new Transaction();
        t.setUser(u);
        t.setProduct(p);
        t.setQuantity(quantity);
        t.setTotalAmount(p.getProductPrice() * quantity);
        t.setTimestamp(Calendar.getInstance());
        t.setStatus(true);

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
