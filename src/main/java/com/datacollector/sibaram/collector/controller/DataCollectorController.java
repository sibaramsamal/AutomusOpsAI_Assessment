package com.datacollector.sibaram.collector.controller;

import com.datacollector.sibaram.collector.entity.MetricsEntity;
import com.datacollector.sibaram.collector.repository.MetricsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("web/api/v1/datacollector")
@Slf4j
public class DataCollectorController {

    private final MetricsRepository metricsRepository;

    @Autowired
    public DataCollectorController (MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }

    @GetMapping
    public ModelAndView getAllFetchedData(@RequestParam(required = false) String source,
                                          Model model) {
        log.info("Fetching Metric Data ...");
        try {
            List<String> sources = metricsRepository.findDistinctSources();
            List<MetricsEntity> data = metricsRepository.getAllMetricsData(source);
            model.addAttribute("sources", sources);
            model.addAttribute("selectedSource", source);
            model.addAttribute("data", data);
            return new ModelAndView("metrics");
        } catch (Exception e) {
            log.error("Error fetching metric data", HttpStatus.INTERNAL_SERVER_ERROR);
            model.addAttribute("error", "Error fetching metric data" + e.getMessage());
            return new ModelAndView("metrics");
        }
    }

    @GetMapping("/pro")
    public ResponseEntity<Map<String, Object>> getMockMetricsPro() {
        List<Map<String, Object>> metrics = new ArrayList<>();

        Map<String, Object> metric1 = new HashMap<>();
        metric1.put("source", "Prometheus");
        metric1.put("metricName", "cpu_usage");
        metric1.put("value", 0.75);
        metric1.put("createdAt", "2024-03-14T10:15:30");
        metrics.add(metric1);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("series", Map.of("metrics", metrics));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/data")
    public ResponseEntity<Map<String, Object>> getMockMetricsData() {
        List<Map<String, Object>> metrics = new ArrayList<>();

        Map<String, Object> metric1 = new HashMap<>();
        metric1.put("source", "DataLog");
        metric1.put("metricName", "cpu_usage");
        metric1.put("value", 0.75);
        metric1.put("createdAt", "2024-03-14T10:15:30");
        metrics.add(metric1);

        Map<String, Object> metric2 = new HashMap<>();
        metric2.put("source", "DataLog");
        metric2.put("metricName", "memory_usage");
        metric2.put("value", 1.25);
        metric2.put("createdAt", "2024-03-14T10:16:00");
        metrics.add(metric2);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("series", Map.of("metrics", metrics));

        return ResponseEntity.ok(response);
    }


}
