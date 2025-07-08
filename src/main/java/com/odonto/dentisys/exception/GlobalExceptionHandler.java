package com.odonto.dentisys.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.odonto.dentisys.dto.ErrorResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DentisysException.class)
    public ResponseEntity<ErrorResponseDTO> handleDentisysException(DentisysException ex, WebRequest request) {
        log.error("Error de Dentisys: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getCodigoError(),
                ex.getTipoError(),
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getCodigoError()));
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleRecursoNoEncontradoException(RecursoNoEncontradoException ex,
            WebRequest request) {
        log.error("Recurso no encontrado: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                404,
                "RECURSO_NO_ENCONTRADO",
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicadoException(DuplicadoException ex, WebRequest request) {
        log.error("Error de duplicación: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                409,
                "DUPLICADO",
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidacionException(ValidacionException ex, WebRequest request) {
        log.error("Error de validación: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                400,
                "VALIDACION",
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest request) {
        log.error("Error de integridad de datos: {}", ex.getMessage(), ex);

        String mensaje = "Error de integridad de datos";
        String tipoError = "INTEGRIDAD_DATOS";

        // Detectar errores específicos de duplicación
        if (ex.getCause() != null && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) ex.getCause()
                    .getCause();
            if (sqlEx.getMessage().contains("Duplicate entry")) {
                mensaje = "No se puede insertar un registro duplicado";
                tipoError = "DUPLICATE_KEY";
            } else if (sqlEx.getMessage().contains("foreign key constraint fails")) {
                mensaje = "No se puede eliminar el registro porque está siendo referenciado por otros datos";
                tipoError = "FOREIGN_KEY_CONSTRAINT";
            }
        }

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                409,
                tipoError,
                mensaje);

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex,
            WebRequest request) {
        log.error("Entidad no encontrada: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                404,
                "ENTIDAD_NO_ENCONTRADA",
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex,
            WebRequest request) {
        log.error("Error de validación de argumentos: {}", ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String descripcion = "Errores de validación: " + errors.toString();

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                400,
                "VALIDACION_ARGUMENTOS",
                descripcion);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex,
            WebRequest request) {
        log.error("Argumento ilegal: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                400,
                "ARGUMENTO_ILEGAL",
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, WebRequest request) {
        log.error("Error interno del servidor: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                500,
                "ERROR_INTERNO",
                "Ha ocurrido un error interno en el servidor");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}