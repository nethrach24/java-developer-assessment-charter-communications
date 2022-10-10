package com.assessment.rewardservice.rewards.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PurchaseTransaction> purchaseTransactions;

    public Customer() {
    }

    public Customer(Long id, String name, Set<PurchaseTransaction> purchaseTransactions) {
        this.id = id;
        this.name = name;
        this.purchaseTransactions = purchaseTransactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PurchaseTransaction> getTransactions() {
        return purchaseTransactions;
    }

    public void setTransactions(Set<PurchaseTransaction> purchaseTransactions) {
        this.purchaseTransactions = purchaseTransactions;
    }
}
