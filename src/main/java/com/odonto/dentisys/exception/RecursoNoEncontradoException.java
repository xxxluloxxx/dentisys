package com.odonto.dentisys.exception;

public class RecursoNoEncontradoException extends DentisysException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje, 404, "RECURSO_NO_ENCONTRADO");
    }

    public RecursoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, 404, "RECURSO_NO_ENCONTRADO", causa);
    }
}