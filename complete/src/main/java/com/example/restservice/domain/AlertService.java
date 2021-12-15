package com.example.restservice.domain;

import java.util.Set;

public interface AlertService {
    public void addAlert(Set<Alert> alertList);
    Set<Alert> searchAlerts(String host, String app, Long startTime, Long endTime);

}
