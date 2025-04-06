package com.odonto.dentisys.config;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import jakarta.annotation.PostConstruct;

@Configuration
public class TimeZoneConfig {

    public static final String ZONA_ECUADOR = "America/Guayaquil";

    @PostConstruct
    public void init() {
        // Establecer la zona horaria predeterminada para toda la aplicación
        TimeZone.setDefault(TimeZone.getTimeZone(ZONA_ECUADOR));
    }

    @Bean
    @Primary
    public ZoneId zoneId() {
        return ZoneId.of(ZONA_ECUADOR);
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of(ZONA_ECUADOR));
    }
}