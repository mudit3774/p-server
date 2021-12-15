package com.example.restservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InmemoryAlertDaoTest {

    @BeforeEach
    void setUp() {
        // Note : All this so that we can have static variables for in-memory dao
        InmemoryAlertDao inmemoryAlertDao = new InmemoryAlertDao();
        inmemoryAlertDao.resetAlerts();
    }

    @Test
    void searchAlerts_ShouldReturnEmptyOnNoAlerts() {
        InmemoryAlertDao inmemoryAlertDao = new InmemoryAlertDao();
        Set<Alert> alerts = inmemoryAlertDao.searchAlerts("*", "*", -1L, -1L);
        assertEquals(alerts, new HashSet<>());
    }

    @Test
    void searchAlerts_ShouldReturnAlerts() {
        InmemoryAlertDao inmemoryAlertDao = new InmemoryAlertDao();
        Alert alert = new Alert("host", "app", "line", 1L, "file");
        Set<Alert> alerts = new HashSet<>();
        alerts.add(alert);
        // Note : Wait, what? Is this a unit test? Yes, we are doing this because we have not moved the static
        // variable in a separate class, which mimics an in-memory DB. If we would have done that, simple mocking would have
        // helped
        inmemoryAlertDao.addAlerts(alerts);
        Set<Alert> alertsResponse = inmemoryAlertDao.searchAlerts("*", "*", -1L, -1L);
        assertEquals(alertsResponse, alerts);
    }

    @Test
    void searchAlerts_ShouldNotReturnAlertsWhenNotMatching() {
        InmemoryAlertDao inmemoryAlertDao = new InmemoryAlertDao();
        Alert alert = new Alert("host", "app", "line", 1L, "file");
        Set<Alert> alerts = new HashSet<>();
        alerts.add(alert);
        inmemoryAlertDao.addAlerts(alerts);
        Set<Alert> alertsResponse = inmemoryAlertDao.searchAlerts("hostess", "*", -1L, -1L);
        assertEquals(alertsResponse, new HashSet<>());
    }

    @Test
    void searchAlerts_ValidateTimeFilterSuccess() {
        InmemoryAlertDao inmemoryAlertDao = new InmemoryAlertDao();
        Alert alert = new Alert("host", "app", "line", 1L, "file");
        Set<Alert> alerts = new HashSet<>();
        alerts.add(alert);
        inmemoryAlertDao.addAlerts(alerts);
        Set<Alert> alertsResponse = inmemoryAlertDao.searchAlerts("host", "*", 0L, 2L);
        assertEquals(alertsResponse, alerts);
    }

    @Test
    void searchAlerts_ValidateTimeFilterFailed() {
        InmemoryAlertDao inmemoryAlertDao = new InmemoryAlertDao();
        Alert alert = new Alert("host", "app", "line", 8L, "file");
        Set<Alert> alerts = new HashSet<>();
        alerts.add(alert);
        inmemoryAlertDao.addAlerts(alerts);
        Set<Alert> alertsResponse = inmemoryAlertDao.searchAlerts("host", "*", 0L, 2L);
        assertEquals(alertsResponse, new HashSet<>());
    }
}