package com.tahrioussama.fraud.services;

import com.tahrioussama.fraud.entities.FraudCheckHistory;
import com.tahrioussama.fraud.repositories.FraudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {
    private final FraudRepository fraudRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
