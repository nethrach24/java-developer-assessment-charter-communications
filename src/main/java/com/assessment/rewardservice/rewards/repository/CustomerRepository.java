package com.assessment.rewardservice.rewards.repository;

import com.assessment.rewardservice.rewards.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
