package com.moamen.audit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
public class AuditLogger {

    public AuditLogger() {
        System.out.println(
                "AuditLogger instance created: " + this
        );
    }

    public void log(String message) {

        System.out.println(
                "[AUDIT] " +
                        LocalDateTime.now() +
                        " - " +
                        message
        );
    }
}