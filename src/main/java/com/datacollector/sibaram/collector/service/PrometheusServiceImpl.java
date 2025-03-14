package com.datacollector.sibaram.collector.service;

import com.datacollector.sibaram.collector.entity.MetricsEntity;
import com.datacollector.sibaram.collector.repository.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PrometheusServiceImpl implements PrometheusService{

    @Value("${prometheusApiUrl}")
    private String PROMETHEUS_API_URL;

    @Autowired
    private MetricsRepository metricsRepository;

    @Override
    public void fetchMetrics() {

        RestTemplate restTemplate = new RestTemplate();

        List<MetricsEntity> metricsEntities = new ArrayList<>();

        Map<String, Object> response = restTemplate.getForObject(PROMETHEUS_API_URL, Map.class);
        if (response != null) {
            Map<String, Object> seriesMap = (Map<String, Object>) response.get("series");
            List<Map<String, Object>> series = (List<Map<String, Object>>) seriesMap.get("metrics");
            for (Map<String, Object> metric : series) {
                MetricsEntity data = MetricsEntity.builder()
                        .source("Prometheus")
                        .metricName(metric.get("metricName").toString())
                        .value(Double.parseDouble(metric.get("value").toString()))
                        .createdAt(new Date())
                        .build();
                metricsEntities.add(data);
            }
            metricsRepository.saveAll(metricsEntities);
        }
    }
}
