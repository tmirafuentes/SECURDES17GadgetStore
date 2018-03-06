package edu.dlsu.securdeproject.services;

import edu.dlsu.securdeproject.classes.Customer;
import edu.dlsu.securdeproject.classes.Transaction;
import edu.dlsu.securdeproject.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public boolean addNewTransaction(Transaction t) {
        transactionRepository.save(t);
        return true;
    }

    public boolean updateTransaction(Transaction t) {
        transactionRepository.save(t);
        return true;
    }

    public Transaction getTransaction(long id) {
        return (Transaction) transactionRepository.findByTransactionId(id);
    }

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public boolean deleteTransaction(Transaction t) {
        transactionRepository.delete(t);
        return true;
    }

    public List<Transaction> getTransactionsByName(Customer c) {
        return (List<Transaction>) transactionRepository.findAllByCustomer(c);
    }
}
