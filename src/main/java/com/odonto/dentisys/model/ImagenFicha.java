package com.odonto.dentisys.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "imagenes_fichas")
public class ImagenFicha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ficha_id", nullable = false)
    private FichaOdontologica ficha;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "imagen", nullable = false)
    @Lob
    @JdbcTypeCode(java.sql.Types.BINARY)
    private byte[] imagen;

    @Column(name = "tipo_imagen", length = 50)
    private String tipoImagen;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}