package com.assessment.rewardservice.rewards.controller;

import com.assessment.rewardservice.rewards.entity.Customer;
import com.assessment.rewardservice.rewards.entity.PurchaseTransaction;
import com.assessment.rewardservice.rewards.model.RewardsResponse;
import com.assessment.rewardservice.rewards.model.TransactionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class RewardsMapperTest {

    @InjectMocks
    private RewardsMapper rewardsMapper;

    @Test
    void mapRewardsResponseSuccess() {
        //given
        List<Customer> customerList = new ArrayList<>();
        customerList.add(getCustomer1());
        //when
        List<RewardsResponse> rewardsResponses = rewardsMapper.map(customerList);
        //then
        Assertions.assertEquals(1, rewardsResponses.size());
        Assertions.assertTrue(rewardsResponses.get(0).getCustomerName().equalsIgnoreCase(customerList.get(0).getName()));
    }

    @Test
    void mapRewardsResponseSuccessWithNull() {
        //given
        //when
        List<RewardsResponse> rewardsResponses = rewardsMapper.map(null);
        //then
        Assertions.assertNull(rewardsResponses);
    }

    @Test
    void mapRewardsResponseOfACustomerSuccess() {
        //given
        Customer customer = getCustomer1();
        //when
        RewardsResponse rewardsResponse = rewardsMapper.mapCustomerIfNotNull(Optional.of(customer));
        //then
        Assertions.assertTrue(rewardsResponse.getCustomerName().equalsIgnoreCase(customer.getName()));
    }

    @Test
    void mapRewardsResponseOfACustomerWithEmptyValueSuccess() {
        //given
        //when
        RewardsResponse rewardsResponse = rewardsMapper.mapCustomerIfNotNull(Optional.empty());
        //then
        Assertions.assertNull(rewardsResponse);
    }


    @Test
    void mapRewardsResponseTransactionDataSuccess() {
        //given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer1");
        List<PurchaseTransaction> transactions = new ArrayList<>(getTransactionsOfCustomer1(customer));
        //when
        Map<String, List<TransactionResponse>> rewardsResponse = rewardsMapper.mapTransaction(transactions);
        //then
        Assertions.assertNotNull(rewardsResponse);
        Assertions.assertTrue(rewardsResponse.containsKey(transactions.get(0).getTransactionDate().getMonth().toString()));
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
