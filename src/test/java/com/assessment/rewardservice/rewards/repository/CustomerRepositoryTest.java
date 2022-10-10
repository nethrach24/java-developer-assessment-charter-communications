package com.assessment.rewardservice.rewards.repository;

import com.assessment.rewardservice.rewards.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldFetchTransactions() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer1");
        customer = customerRepository.save(customer);
        // when
        List<Customer> customers = customerRepository.findAll();

        // then
        assertNotNull(customers);
        assertTrue(customers.size() > 0);
        assertEquals(customer.getName(), customers.get(0).getName());
    }
}