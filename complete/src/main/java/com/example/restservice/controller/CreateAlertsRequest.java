package com.example.restservice.controller;

import com.example.restservice.domain.Alert;

import java.util.List;
import java.util.Set;

public class CreateAlertsRequest {
    private Set<Alert> alerts;

    public CreateAlertsRequest() {
    }

    public void setAlerts(Set<Alert> alerts) {
        this.alerts = alerts;
    }

    public CreateAlertsRequest(Set<Alert> alertList) {
        this.alerts = alertList;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }
}
