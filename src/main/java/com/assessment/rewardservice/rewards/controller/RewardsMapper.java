package com.assessment.rewardservice.rewards.controller;

import com.assessment.rewardservice.rewards.entity.Customer;
import com.assessment.rewardservice.rewards.entity.PurchaseTransaction;
import com.assessment.rewardservice.rewards.model.RewardsResponse;
import com.assessment.rewardservice.rewards.model.TransactionResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RewardsMapper {
    private Double totalAmount;
    private Long totalPoints;

    public List<RewardsResponse> map(List<Customer> customers) {
        if (Optional.ofNullable(customers).isPresent()) {
            return customers.stream().map(this::map).collect(Collectors.toList());
        }
        return null;
    }

    public RewardsResponse mapCustomerIfNotNull(Optional<Customer> customer) {
        if (Objects.nonNull(customer) && customer.isPresent()) {
            return this.map(customer.get());
        }
        return null;
    }

    public Map<String, List<TransactionResponse>> mapTransaction(List<PurchaseTransaction> transactions) {
        return this.map(new HashSet<>(transactions), false);
    }

    private RewardsResponse map(Customer customer) {
        this.totalAmount = 0d;
        this.totalPoints = 0l;
        RewardsResponse rewardsResponse = new RewardsResponse();
        rewardsResponse.setCustomerId(customer.getId());
        rewardsResponse.setCustomerName(customer.getName());
        rewardsResponse.setTransactions(this.map(customer.getTransactions(), true));
        rewardsResponse.setRewardPointsEarned(totalPoints);
        rewardsResponse.setTotalPurchasedAmount(totalAmount);
        return rewardsResponse;
    }

    private Map<String, List<TransactionResponse>> map(Set<PurchaseTransaction> transactions, boolean isTotalToCalculate) {
        Map<String, List<TransactionResponse>> transactionsListMap = new HashMap<>();
        LocalDateTime lastThreeMonths = LocalDateTime.now().minusMonths(3);
        for (PurchaseTransaction transaction : transactions) {
            if (transaction.getTransactionDate().isAfter(lastThreeMonths)) {
                String month = String.valueOf(transaction.getTransactionDate().getMonth());
                List<TransactionResponse> transactionResponses = transactionsListMap.getOrDefault(month, new ArrayList<>());
                transactionResponses.add(map(transaction, isTotalToCalculate));
                transactionsListMap.put(month, transactionResponses);
            }
        }
        return transactionsListMap;
    }

    private TransactionResponse map(PurchaseTransaction transaction, boolean isTotalToCalculate) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());
        transactionResponse.setTransactionDate(transaction.getTransactionDate().toString());
        transactionResponse.setAmount(transaction.getAmount());
        Long points = transaction.getRewardPoints();
        transactionResponse.setRewardPointsEarned(points);
        if (isTotalToCalculate) {
            this.totalPoints += points;
            this.totalAmount += transaction.getAmount();
        }
        return transactionResponse;
    }
}
