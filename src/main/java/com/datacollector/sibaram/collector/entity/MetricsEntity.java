package com.datacollector.sibaram.collector.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "metrics_data")
public class MetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String source;

    @Column(name = "metricname")
    private String metricName;

    private double value;

    @Column(name = "createdat")
    private Date createdAt;
}