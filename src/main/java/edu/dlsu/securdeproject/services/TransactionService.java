package edu.dlsu.securdeproject.services;

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
        return (Transaction) TransactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) TransactionRepository.findAll();
    }

    public List<Transaction> getAllTransactions(String transactionName) {
        return (List<Transaction) transactionRepository.findAllByTransactionName(transactionName);
    }

    public boolean deleteTransaction(Transaction p) {
        TransactionRepository.delete(p);
        return true;
    }
}
