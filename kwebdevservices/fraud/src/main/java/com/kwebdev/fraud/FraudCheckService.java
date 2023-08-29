package com.kwebdev.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

//    replaced with @AllArgsConstructor
//    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
//        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
//    }
    public boolean isFraudulentCustomer(Integer customerId) {
        // ideally would use some internal or 3rd party algorithm to determine fraudulent or suspicious users
        this.fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudulentUser(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
