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

    public static final String SOCALERTS = "socalerts";
    public static final String SERVICE = "service";
    public static final String ALERTS = "alerts";
    private final AlertService alertService;

    public AlertController() {
        this.alertService = new SimpleAlertService();
    }

    // Note : This is exposed for local testing and not intended to be used in production
    @PostMapping(path = ALERTS,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void createAlerts(@RequestBody CreateAlertsRequest request) {
        alertService.addAlert(request.getAlerts());
    }

    @KafkaListener(topics = SOCALERTS, groupId = SERVICE)
    public void listenAlerts(String alertString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Set<Alert> alerts = mapper.readValue(alertString, CreateAlertsRequest.class).getAlerts();
        alertService.addAlert(alerts);
    }
}
