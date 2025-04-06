package com.odonto.dentisys.model.base;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.odonto.dentisys.config.TimeZoneConfig;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        ZoneId zonaEcuador = ZoneId.of(TimeZoneConfig.ZONA_ECUADOR);
        createdAt = ZonedDateTime.now(zonaEcuador);
        updatedAt = ZonedDateTime.now(zonaEcuador);
    }

    @PreUpdate
    protected void onUpdate() {
        ZoneId zonaEcuador = ZoneId.of(TimeZoneConfig.ZONA_ECUADOR);
        updatedAt = ZonedDateTime.now(zonaEcuador);
    }
}