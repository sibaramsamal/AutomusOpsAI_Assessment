package com.datacollector.sibaram.collector.repository;

import com.datacollector.sibaram.collector.entity.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetricsRepository extends JpaRepository<MetricsEntity, Integer> {

    @Query("SELECT DISTINCT m.source FROM MetricsEntity m")
    List<String> findDistinctSources();

    @Query("SELECT m FROM MetricsEntity m WHERE :source IS NULL OR m.source = :source")
    List<MetricsEntity> getAllMetricsData(@Param("source") String source);
}
