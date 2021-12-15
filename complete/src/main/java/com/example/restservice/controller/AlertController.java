package com.example.restservice.controller;

import com.example.restservice.domain.Alert;
import com.example.restservice.domain.AlertService;
import com.example.restservice.domain.SimpleAlertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
        alertService.addAlert(request.getAlerts());
    }

    @KafkaListener(topics = "socalerts", groupId = "service")
    public void listenAlerts(String alertString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Set<Alert> alerts = mapper.readValue(alertString, CreateAlertsRequest.class).getAlerts();
        alertService.addAlert(alerts);
    }
}
