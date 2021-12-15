package com.example.restservice.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InmemoryAlertDao implements AlertDao {

    // Note : Yes, we all hate static variable, but it is used as
    // a replacement for a data store which should not be present in
    // the actual implementation of alert DAO
    // TODO : Move this to separate class to make this class testable
    private static Set<Alert> alerts = new HashSet<>();

    public InmemoryAlertDao() {
    }

    // Note : This ugly looking function is to reset alerts for testing. Again,
    // this complication is introduced only in the in-memory DAO
    public void resetAlerts() {
        alerts = new HashSet<>();
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
