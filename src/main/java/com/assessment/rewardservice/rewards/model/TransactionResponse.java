package com.assessment.rewardservice.rewards.model;

public class TransactionResponse {
    private Long id;
    private Double amount;
    private Long rewardPointsEarned;
    private String transactionDate;

    public TransactionResponse() {}
    public TransactionResponse(Double amount, Long rewardPointsEarned, String transactionDate) {
        this.amount = amount;
        this.rewardPointsEarned = rewardPointsEarned;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getRewardPointsEarned() {
        return rewardPointsEarned;
    }

    public void setRewardPointsEarned(Long rewardPointsEarned) {
        this.rewardPointsEarned = rewardPointsEarned;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
