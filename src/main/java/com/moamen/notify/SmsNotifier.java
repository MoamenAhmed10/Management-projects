package com.moamen.notify;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class SmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS notification: " + message);
    }

}
