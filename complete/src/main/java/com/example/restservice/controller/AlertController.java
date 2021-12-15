package com.example.restservice.controller;

import com.example.restservice.domain.AlertService;
import com.example.restservice.domain.SimpleAlertService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {

    private final AlertService alertService;

    public AlertController() {
        this.alertService = new SimpleAlertService();
    }

    @PostMapping(path = "alerts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void createAlerts(@RequestBody CreateAlertsRequest request) {
       alertService.addAlerts(request.getAlerts());
    }
}
