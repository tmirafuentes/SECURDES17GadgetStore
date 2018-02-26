package edu.dlsu.securdeproject.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction { 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long transactionId;
    private int qtyBought;
    private Datetime transTimestamp;
    private String status;
}
