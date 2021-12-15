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

    private final AlertService alertService;

    public SearchController() {
        this.alertService = new SimpleAlertService();
    }

    @GetMapping("/search")
    public Set<Alert> search(@RequestParam(value = "host", defaultValue = "*") String host,
                             @RequestParam(value = "application", defaultValue = "*") String application,
                             @RequestParam(value = "startTime", defaultValue = "-1") String startTime,
                             @RequestParam(value = "endTime", defaultValue = "-1") String endTime) {
        System.out.println(host + application + startTime + endTime);
        return alertService.searchAlerts(host, application, Long.parseLong(startTime), Long.parseLong(endTime));
    }
}
