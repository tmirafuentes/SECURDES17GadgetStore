package edu.dlsu.securdeproject.classes;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Transaction { 
    private Long transactionId;
    private User user;
    private Product product;
    private int quantity;
    private double totalAmount;
    private Calendar timestamp;
    private boolean status;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public void setTotalAmount() {
        totalAmount = getQuantity() * getProduct().getProductPrice();
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
 