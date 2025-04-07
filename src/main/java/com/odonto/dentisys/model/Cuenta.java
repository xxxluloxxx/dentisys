package com.odonto.dentisys.model;

import java.time.LocalDate;

import com.odonto.dentisys.model.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cuentas")
public class Cuenta extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "cobranza_id")
    private Cobranza cobranza;

    @Column(name = "monto", nullable = false, columnDefinition = "numeric")
    private Double monto;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDate fechaMovimiento;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;
}