package com.moamen.config;

import org.springframework.beans.factory.annotation.Value;

public class DreamCompany {

    @Value("${company.name}")
    private String companyName;

    @Value("${company.currency}")
    private String currency;

    @Value("${notification.retry-count}")
    private int notificationRetryCount;

    @Value("${raise.max-percentage}")
    private double maxRaisePercentage;

        public DreamCompany(String dreamCompany) {
        this.companyName = dreamCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCurrency() {
        return currency;
    }

    public int getNotificationRetryCount() {
        return notificationRetryCount;
    }

    public double getMaxRaisePercentage() {
        return maxRaisePercentage;
    }

}
