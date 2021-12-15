package com.example.restservice.domain;

import java.util.List;
import java.util.Set;

public interface AlertService {
    public void addAlerts(List<Alert> alertList);
    Set<Alert> searchAlerts(String host, String app, Long startTime, Long endTime);

}
