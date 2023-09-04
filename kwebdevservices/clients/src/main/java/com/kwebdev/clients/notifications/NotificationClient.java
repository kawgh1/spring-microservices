package com.kwebdev.clients.notifications;

import com.kwebdev.clients.fraud.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("notifications")
public interface NotificationClient {
    @GetMapping(path = "api/v1/notifications/{customerId}")
    FraudCheckResponse isFraudulentCustomer(
            @PathVariable("customerId") Integer customerId);
}