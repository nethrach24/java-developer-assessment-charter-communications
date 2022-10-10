package com.assessment.rewardservice.rewards.repository;

import com.assessment.rewardservice.rewards.entity.Customer;
import com.assessment.rewardservice.rewards.entity.PurchaseTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void shouldFetchTransactions() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer1");
        customer = customerRepository.save(customer);

        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(1000d);
        transaction.setCustomer(customer);
        transaction.setDescription("Description");
        transactionRepository.save(transaction);

        // when
        List<PurchaseTransaction> transactionList = transactionRepository.findAllByTransactionDateAfter(LocalDateTime.now().minusMonths(3));

        // then
        assertNotNull(transactionList);
        assertTrue(transactionList.size() > 0);
        assertEquals(transaction.getAmount(), transactionList.get(0).getAmount());
    }
}