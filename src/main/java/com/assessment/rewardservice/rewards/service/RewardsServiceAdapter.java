package com.assessment.rewardservice.rewards.service;

import com.assessment.rewardservice.rewards.entity.Customer;
import com.assessment.rewardservice.rewards.entity.PurchaseTransaction;
import com.assessment.rewardservice.rewards.repository.CustomerRepository;
import com.assessment.rewardservice.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RewardsServiceAdapter implements RewardsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Customer> getRewards() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getRewardsByCustomer(Long customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public List<PurchaseTransaction> getTransactions() {
		LocalDateTime startDate = LocalDateTime.now().minusMonths(3);
		return transactionRepository.findAllByTransactionDateAfter(startDate);
	}
}
