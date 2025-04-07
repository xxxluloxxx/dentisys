package com.odonto.dentisys.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.odonto.dentisys.config.TimeZoneConfig;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "proformas")
public class Proforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "subtotal", nullable = false, columnDefinition = "numeric")
    private Double subtotal;

    @Column(name = "iva", nullable = false, columnDefinition = "numeric")
    private Double iva;

    @Column(name = "descuento", nullable = false, columnDefinition = "numeric")
    private Double descuento;

    @Column(name = "total", nullable = false, columnDefinition = "numeric")
    private Double total;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @OneToMany(mappedBy = "proforma", cascade = jakarta.persistence.CascadeType.ALL)
    private List<DetalleProforma> detalles;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (fechaEmision == null) {
            ZoneId zonaEcuador = ZoneId.of(TimeZoneConfig.ZONA_ECUADOR);
            fechaEmision = LocalDate.now(zonaEcuador);
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}