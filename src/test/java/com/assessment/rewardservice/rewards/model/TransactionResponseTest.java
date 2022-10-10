package com.assessment.rewardservice.rewards.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionResponseTest {

    @Test
    void testMapTheTransactionDataWithArgConstructor() {
        LocalDateTime currentDate = LocalDateTime.now();
        TransactionResponse transactionResponse = new TransactionResponse(1000d, 12L, currentDate.toString());

        assertNotNull(transactionResponse);
        assertEquals(1000d, transactionResponse.getAmount());
        assertEquals(currentDate.toString(), transactionResponse.getTransactionDate());
        assertEquals(12L, transactionResponse.getRewardPointsEarned());

    }

    @Test
    void testMapTheTransactionDataWithNoArgConstructor() {
        LocalDateTime currentDate = LocalDateTime.now();
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(1000d);
        transactionResponse.setRewardPointsEarned(12L);
        transactionResponse.setTransactionDate(currentDate.toString());

        assertNotNull(transactionResponse);
        assertEquals(1000d, transactionResponse.getAmount());
        assertEquals(currentDate.toString(), transactionResponse.getTransactionDate());
        assertNotEquals(0L, transactionResponse.getId());

    }
}