package com.assessment.rewardservice.rewards.service;

import com.assessment.rewardservice.rewards.entity.Customer;
import com.assessment.rewardservice.rewards.entity.PurchaseTransaction;
import com.assessment.rewardservice.rewards.repository.CustomerRepository;
import com.assessment.rewardservice.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RewardsServiceAdapterTest {

    @InjectMocks
    private RewardsServiceAdapter rewardsService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void testShouldFetchTheRewardsSuccess() {
        //given
        List<Customer> customerList = new ArrayList<>();
        customerList.add(getCustomer1());
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);
        //when
        List<Customer> customersResult = rewardsService.getRewards();
        //Then
        assertNotNull(customersResult);
        assertTrue(customersResult.size() > 0);
    }

    @Test
    void testShouldFetchTheRewardsOfACustomerSuccess() {
        //given
        Customer customer = getCustomer1();
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
        //when
        Optional<Customer> customersResult = rewardsService.getRewardsByCustomer(customer.getId());
        //Then
        assertNotNull(customersResult);
        assertTrue(customersResult.isPresent());
        assertEquals(customer.getName(), customersResult.get().getName());
    }

    @Test
    void testShouldFetchTheRewardsOfACustomerNotFound() {
        //given
        //when
        Optional<Customer> customersResult = rewardsService.getRewardsByCustomer(2L);
        //Then
        assertFalse(customersResult.isPresent());
    }

    @Test
    void testShouldFetchTheTransactionSuccess() {
        //given
        Customer customer = new Customer();
        customer.setName("Customer1");
        customer.setId(1L);
        List<PurchaseTransaction> transactions = new ArrayList<>(getTransactionsOfCustomer1(customer));
        Mockito.when(transactionRepository.findAllByTransactionDateAfter(Mockito.any())).thenReturn(transactions);
        //when
        List<PurchaseTransaction> transactionsResult = rewardsService.getTransactions();
        //Then
        assertNotNull(transactionsResult);
        assertTrue(transactionsResult.size() > 0);
    }

    private Customer getCustomer1() {
        Customer customer = new Customer();
        customer.setName("Customer1");
        customer.setId(1L);
        customer.setTransactions(getTransactionsOfCustomer1(customer));
        return customer;
    }

    private Set<PurchaseTransaction> getTransactionsOfCustomer1(Customer customer) {
        Set<PurchaseTransaction> transactions = new HashSet<>();
        transactions.add(getTransaction(1L, customer, 1000d, LocalDateTime.now()));
        transactions.add(getTransaction(2L, customer, 1234d, LocalDateTime.now()));
        transactions.add(getTransaction(3L, customer, 789d, LocalDateTime.now()));
        return transactions;
    }

    private PurchaseTransaction getTransaction(long id, Customer customer, Double amount, LocalDateTime date) {
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setCustomer(customer);
        transaction.setId(id);
        transaction.setAmount(amount);
        transaction.setTransactionDate(date);
        return transaction;
    }
}
