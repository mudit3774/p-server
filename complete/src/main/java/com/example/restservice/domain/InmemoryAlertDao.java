package com.example.restservice.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InmemoryAlertDao implements AlertDao {

    private static final Set<Alert> alerts = new HashSet<>();

    public InmemoryAlertDao() {
    }

    @Override
    public void addAlerts(Set<Alert> alertList) {
        alerts.addAll(alertList);
    }

    @Override
    public Set<Alert> searchAlerts(String host, String app, Long startTime, Long endTime) {
        Set<Alert> tmpAlertList = new HashSet<>();
        alerts.forEach(
                alert -> {
                    System.out.println(alert.toString());
                    if ((host.equals("*") || alert.getHost().equals(host))
                            && (app.equals("*") || alert.getApp().equals(app))
                            && (startTime < 0 || alert.getTimestamp() >= startTime)
                            && (endTime < 0 || alert.getTimestamp() <= endTime)) {
                        tmpAlertList.add(alert);
                    }
                }
        );
        return tmpAlertList;
    }
}
