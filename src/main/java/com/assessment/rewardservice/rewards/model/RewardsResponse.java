package com.assessment.rewardservice.rewards.model;

import java.util.List;
import java.util.Map;

public class RewardsResponse {
    private Long customerId;
    private String customerName;
    private Long rewardPointsEarned;
    private Double totalPurchasedAmount;
    private Map<String, List<TransactionResponse>> transactions;

    public RewardsResponse() {}

    public RewardsResponse(Long customerId, String customerName, Long rewardPointsEarned, Double totalPurchasedAmount, Map<String, List<TransactionResponse>> transactions) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.rewardPointsEarned = rewardPointsEarned;
        this.totalPurchasedAmount = totalPurchasedAmount;
        this.transactions = transactions;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getRewardPointsEarned() {
        return rewardPointsEarned;
    }

    public void setRewardPointsEarned(Long rewardPointsEarned) {
        this.rewardPointsEarned = rewardPointsEarned;
    }

    public Double getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public void setTotalPurchasedAmount(Double totalPurchasedAmount) {
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

    public Map<String, List<TransactionResponse>> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<String, List<TransactionResponse>> transactions) {
        this.transactions = transactions;
    }
}
