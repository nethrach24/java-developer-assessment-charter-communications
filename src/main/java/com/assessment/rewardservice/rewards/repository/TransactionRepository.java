package com.assessment.rewardservice.rewards.repository;

import com.assessment.rewardservice.rewards.entity.PurchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<PurchaseTransaction, Long> {
    List<PurchaseTransaction> findAllByTransactionDateAfter(LocalDateTime from);
}
