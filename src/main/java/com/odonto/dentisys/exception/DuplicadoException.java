package com.odonto.dentisys.exception;

public class DuplicadoException extends DentisysException {

    public DuplicadoException(String mensaje) {
        super(mensaje, 409, "DUPLICADO");
    }

    public DuplicadoException(String mensaje, Throwable causa) {
        super(mensaje, 409, "DUPLICADO", causa);
    }
}