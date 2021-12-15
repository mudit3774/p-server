package com.example.restservice.controller;

import com.example.restservice.domain.Alert;

import java.util.List;

public class CreateAlertsRequest {
    private List<Alert> alerts;

    public CreateAlertsRequest() {
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public CreateAlertsRequest(List<Alert> alertList) {
        this.alerts = alertList;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }
}
