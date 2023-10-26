package com.starHealth.kafkaEg.influxDBConfig;

import org.influxdb.InfluxDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDBConfig {
    @Value("${influxdb.url}")
    private String influxDbUrl;

    @Value("${influxdb.database}")
    private String database;

    @Bean
    public InfluxDB influxDB() {
        return InfluxDBFactory.connect(influxDbUrl);
    }
}