package com.assessment.rewardservice;

import com.assessment.rewardservice.rewards.entity.Customer;
import com.assessment.rewardservice.rewards.entity.PurchaseTransaction;
import com.assessment.rewardservice.rewards.repository.CustomerRepository;
import com.assessment.rewardservice.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class RewardServiceApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(RewardServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = new Customer();
		customer.setName("Customer1");
		Customer result1 = customerRepository.save(customer);
		customer = new Customer();
		customer.setName("Customer2");
		Customer result2 = customerRepository.save(customer);
		customer = new Customer();
		customer.setName("Customer3");
		Customer result3 = customerRepository.save(customer);

		transactionRepository.save(getTransaction(result1, 1000d, "Record1", LocalDateTime.of(2022, 2,2, 0, 0)));
		transactionRepository.save(getTransaction(result2, 500d, "Record2", LocalDateTime.of(2022, 4,2, 0, 0)));
		transactionRepository.save(getTransaction(result3, 1000d, "Record3", LocalDateTime.of(2022, 6,2, 0, 0)));
		transactionRepository.save(getTransaction(result1, 300d, "Record4", LocalDateTime.now()));
		transactionRepository.save(getTransaction(result1, 200d, "Record4", LocalDateTime.now()));
		transactionRepository.save(getTransaction(result1, 50d, "Record4", LocalDateTime.now()));
		transactionRepository.save(getTransaction(result2, 1000d, "Record5", LocalDateTime.now()));
		transactionRepository.save(getTransaction(result3, 1000d, "Record6", LocalDateTime.now()));
	}

	private PurchaseTransaction getTransaction(Customer customer, Double amount, String description, LocalDateTime transactionDate) {
		PurchaseTransaction purchaseTransaction = new PurchaseTransaction();
		purchaseTransaction.setCustomer(customer);
		purchaseTransaction.setAmount(amount);
		purchaseTransaction.setDescription(description);
		purchaseTransaction.setTransactionDate(transactionDate);
		return purchaseTransaction;
	}
}
