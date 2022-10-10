package com.assessment.rewardservice.rewards.service;

import com.assessment.rewardservice.rewards.entity.Customer;
import com.assessment.rewardservice.rewards.entity.PurchaseTransaction;

import java.util.List;
import java.util.Optional;

public interface RewardsService {
    List<Customer> getRewards();
    Optional<Customer> getRewardsByCustomer(Long customerId);
    List<PurchaseTransaction> getTransactions();
}
