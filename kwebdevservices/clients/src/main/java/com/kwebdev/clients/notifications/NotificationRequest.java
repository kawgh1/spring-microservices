package com.kwebdev.clients.notifications;

public record NotificationRequest (
        Integer toCustomerId,
        String toCustomerEmail,
        String message
) {}
