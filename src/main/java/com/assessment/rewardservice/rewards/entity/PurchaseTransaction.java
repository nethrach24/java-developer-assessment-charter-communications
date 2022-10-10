package com.assessment.rewardservice.rewards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class PurchaseTransaction {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Customer customer;

    private Double amount;
    private String description;
    private Long rewardPoints;

    private LocalDateTime transactionDate;

    public PurchaseTransaction() {
    }

    public PurchaseTransaction(Long id, Customer customer, Double amount, String description, Long rewardPoints, LocalDateTime transactionDate) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.description = description;
        this.rewardPoints = rewardPoints;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRewardPoints() {
        this.rewardPoints = 0l;
        if (this.amount > 50 && this.amount <= 100) {
            this.rewardPoints = (long) this.amount.doubleValue() - 50;
        }

        if (this.amount > 100) {
            this.rewardPoints += 50;
            this.rewardPoints += (this.amount.intValue() - 100) * 2L;
        }

        return this.rewardPoints;
    }

    public void setRewardPoints(Long rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
