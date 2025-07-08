package com.odonto.dentisys.exception;

public class ValidacionException extends DentisysException {

    public ValidacionException(String mensaje) {
        super(mensaje, 400, "VALIDACION");
    }

    public ValidacionException(String mensaje, Throwable causa) {
        super(mensaje, 400, "VALIDACION", causa);
    }
}