package com.kwebdev.fraud;

import com.kwebdev.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

//    This is replaced by @AllArgsConstructor annotation
//    public FraudController(FraudCheckService fraudCheckService) {
//        this.fraudCheckService = fraudCheckService;
//    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudulentUser(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentUser = fraudCheckService.isFraudulentCustomer((customerId));
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse((isFraudulentUser));
    }
}
