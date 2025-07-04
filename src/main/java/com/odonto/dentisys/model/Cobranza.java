package com.odonto.dentisys.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.odonto.dentisys.config.TimeZoneConfig;
import com.odonto.dentisys.model.Banco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cobranzas")
public class Cobranza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proforma_id", nullable = false, columnDefinition = "BIGINT")
    private Proforma proforma;

    @ManyToOne
    @JoinColumn(name = "banco_id", columnDefinition = "BIGINT")
    private Banco banco;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @Column(name = "monto", nullable = false, columnDefinition = "numeric")
    private Double monto;

    @Column(name = "metodo_pago", nullable = false, length = 50)
    private String metodoPago;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (fechaPago == null) {
            ZoneId zonaEcuador = ZoneId.of(TimeZoneConfig.ZONA_ECUADOR);
            fechaPago = LocalDate.now(zonaEcuador);
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}