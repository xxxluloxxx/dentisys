package com.odonto.dentisys.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import com.odonto.dentisys.config.TimeZoneConfig;
import com.odonto.dentisys.model.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "proformas")
public class Proforma extends BaseEntity {
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

    @Column(name = "total", nullable = false, columnDefinition = "numeric")
    private Double total;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @OneToMany(mappedBy = "proforma", cascade = jakarta.persistence.CascadeType.ALL)
    private List<DetalleProforma> detalles;

    @Override
    @PrePersist
    protected void onCreate() {
        super.onCreate();
        if (fechaEmision == null) {
            ZoneId zonaEcuador = ZoneId.of(TimeZoneConfig.ZONA_ECUADOR);
            fechaEmision = LocalDate.now(zonaEcuador);
        }
    }
}