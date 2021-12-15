package com.example.restservice.domain;

import java.util.List;
import java.util.Set;

public class SimpleAlertService implements AlertService {

    private final AlertDao alertDao;

    public SimpleAlertService() {
        this.alertDao = new InmemoryAlertDao();
    }

    @Override
    public void addAlerts(List<Alert> alertList) {
        this.alertDao.addAlerts(alertList);
    }

    @Override
    public Set<Alert> searchAlerts(String host, String app, Long startTime, Long endTime) {
        return this.alertDao.searchAlerts(host, app, startTime, endTime);
    }
}
