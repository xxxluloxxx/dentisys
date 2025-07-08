package com.odonto.dentisys.exception;

import lombok.Getter;

@Getter
public class DentisysException extends RuntimeException {
    private final Integer codigoError;
    private final String tipoError;

    public DentisysException(String mensaje, Integer codigoError, String tipoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.tipoError = tipoError;
    }

    public DentisysException(String mensaje, Integer codigoError, String tipoError, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.tipoError = tipoError;
    }
}