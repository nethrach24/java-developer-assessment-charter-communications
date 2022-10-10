package com.assessment.rewardservice.rewards.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RewardsResponseTest {

    @Test
    void testRewardsResponseWithArgConstructor() {
        //when
        RewardsResponse rewardsResponse = new RewardsResponse(1L, "Customer1", 0L, 0d, null);
        //then
        assertNotNull(rewardsResponse);
        assertEquals(1L, rewardsResponse.getCustomerId());
        assertEquals("Customer1", rewardsResponse.getCustomerName());
        assertEquals(0L, rewardsResponse.getRewardPointsEarned());
        assertEquals(0d, rewardsResponse.getTotalPurchasedAmount());
        assertNull(rewardsResponse.getTransactions());
    }

    @Test
    void testRewardsResponseWithNoArgConstructor() {
        //when
        RewardsResponse rewardsResponse = new RewardsResponse();
        rewardsResponse.setCustomerId(1L);
        rewardsResponse.setCustomerName("Customer1");
        rewardsResponse.setRewardPointsEarned(0L);
        rewardsResponse.setTotalPurchasedAmount(0d);
        rewardsResponse.setTransactions(null);
        //then
        assertNotNull(rewardsResponse);
        assertEquals(1L, rewardsResponse.getCustomerId());
        assertEquals("Customer1", rewardsResponse.getCustomerName());
    }
}