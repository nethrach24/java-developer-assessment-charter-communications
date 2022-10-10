package com.assessment.rewardservice.rewards.controller;

import com.assessment.rewardservice.rewards.model.RewardsResponse;
import com.assessment.rewardservice.rewards.model.TransactionResponse;
import com.assessment.rewardservice.rewards.service.RewardsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class RewardsController {

    private final RewardsService rewardsService;
    private final RewardsMapper rewardsMapper;

    @Autowired
    public RewardsController(RewardsService rewardsService, RewardsMapper rewardsMapper) {
        this.rewardsService = rewardsService;
        this.rewardsMapper = rewardsMapper;
    }

    @ApiOperation(value = "Fetch the last 3 months Rewards & Transactions for all the Customers", nickname = "rewards")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = ResponseEntity.class, responseContainer = "List") })
    @GetMapping("/rewards")
    public ResponseEntity<List<RewardsResponse>> getRewards() {
        List<RewardsResponse> result = rewardsMapper.map(rewardsService.getRewards());
        if (Objects.isNull(result)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Fetch the last 3 months Rewards & Transactions for a specific Customer", nickname = "rewardsCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = ResponseEntity.class, responseContainer = "RewardsResponse") })
    @GetMapping("/rewards/customer/{id}")
    public ResponseEntity<RewardsResponse> getRewardsByCustomer(@PathVariable(value = "id") Long customerId) {
        RewardsResponse result = rewardsMapper.mapCustomerIfNotNull(rewardsService.getRewardsByCustomer(customerId));
        if (Objects.isNull(result)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Fetch the last 3 months all Transactions group by month", nickname = "transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = ResponseEntity.class, responseContainer = "Transactions") })
    @GetMapping("/transactions")
    public ResponseEntity<Map<String, List<TransactionResponse>>> getTransactions() {
        Map<String, List<TransactionResponse>> result = rewardsMapper.mapTransaction(rewardsService.getTransactions());
        if (Objects.isNull(result)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(result);
    }
}
