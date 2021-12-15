package com.example.restservice.controller;

import java.util.List;
import java.util.Set;

import com.example.restservice.domain.Alert;
import com.example.restservice.domain.AlertService;
import com.example.restservice.domain.SimpleAlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    public static final String HOST = "host";
    public static final String STAR = "*";
    public static final String APPLICATION = "application";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";
    public static final String MINUS_ONE = "-1";

    private final AlertService alertService;

    public SearchController() {
        this.alertService = new SimpleAlertService();
    }

    // TODO : Paginate API
    // TODO : Add validations -> startTime <= endTime [BadRequest, 400]
    // TODO : Add exception translation
    @GetMapping("v" + MINUS_ONE + "/alert/search")
    public Set<Alert> search(@RequestParam(value = HOST, defaultValue = STAR) String host,
                             @RequestParam(value = APPLICATION, defaultValue = STAR) String application,
                             @RequestParam(value = START_TIME, defaultValue = MINUS_ONE) String startTime,
                             @RequestParam(value = END_TIME, defaultValue = MINUS_ONE) String endTime) {
        return alertService.searchAlerts(host, application, Long.parseLong(startTime), Long.parseLong(endTime));
    }
}
