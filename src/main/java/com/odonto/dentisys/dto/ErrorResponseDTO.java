package com.odonto.dentisys.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private Integer exception;
    private String error;
    private String descripcion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponseDTO(Integer exception, String error, String descripcion) {
        this.exception = exception;
        this.error = error;
        this.descripcion = descripcion;
        this.timestamp = LocalDateTime.now();
    }
}