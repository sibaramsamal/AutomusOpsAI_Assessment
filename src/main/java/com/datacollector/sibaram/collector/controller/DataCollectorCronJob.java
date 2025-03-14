package com.datacollector.sibaram.collector.controller;

import com.datacollector.sibaram.collector.service.DatalogService;
import com.datacollector.sibaram.collector.service.PrometheusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataCollectorCronJob {

    private final DatalogService datalogService;
    private final PrometheusService prometheusService;

    @Autowired
    public DataCollectorCronJob (DatalogService datalogService, PrometheusService prometheusService) {
        this.datalogService = datalogService;
        this.prometheusService = prometheusService;
    }

    @Scheduled(fixedRate = 30000)
    public void collectMetrics() {
        log.info("Inside cron job...");
        datalogService.fetchMetrics();
        prometheusService.fetchMetrics();
    }
}
