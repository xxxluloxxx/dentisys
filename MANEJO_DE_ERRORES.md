# Sistema de Manejo de Errores - Dentisys

## Descripción General

Se ha implementado un sistema completo de manejo de errores personalizado que devuelve respuestas estructuradas con el siguiente formato:

```json
{
    "exception": 500,
    "error": "DUPLICATE_KEY",
    "descripcion": "No se puede insertar un mismo id para la tabla pacientes",
    "timestamp": "2024-01-15 10:30:45"
}
```

## Componentes del Sistema

### 1. ErrorResponseDTO
Clase que define la estructura de respuesta de error:
- `exception`: Código HTTP del error
- `error`: Tipo de error (identificador)
- `descripcion`: Descripción detallada del error
- `timestamp`: Fecha y hora del error

### 2. Excepciones Personalizadas

#### DentisysException (Base)
Excepción base para todas las excepciones personalizadas de la aplicación.

#### RecursoNoEncontradoException (404)
Se lanza cuando no se encuentra un recurso solicitado.
```java
throw new RecursoNoEncontradoException("No se encontró el paciente con ID: " + id);
```

#### DuplicadoException (409)
Se lanza cuando se intenta crear un registro duplicado.
```java
throw new DuplicadoException("Ya existe un paciente con la identificación: " + identificacion);
```

#### ValidacionException (400)
Se lanza cuando hay errores de validación en los datos.
```java
throw new ValidacionException("El email es obligatorio");
```

### 3. GlobalExceptionHandler
Manejador global que captura todas las excepciones y las convierte en respuestas estructuradas.

## Tipos de Errores Manejados

### Errores de Base de Datos
- **DUPLICATE_KEY**: Clave duplicada
- **FOREIGN_KEY_CONSTRAINT**: Violación de clave foránea
- **INTEGRIDAD_DATOS**: Otros errores de integridad

### Errores de Validación
- **VALIDACION**: Errores de validación generales
- **VALIDACION_ARGUMENTOS**: Errores en argumentos de métodos
- **ARGUMENTO_ILEGAL**: Argumentos ilegales

### Errores de Recursos
- **RECURSO_NO_ENCONTRADO**: Recurso no encontrado
- **ENTIDAD_NO_ENCONTRADA**: Entidad no encontrada

### Errores Generales
- **ERROR_INTERNO**: Error interno del servidor

## Ejemplos de Uso

### En Servicios
```java
@Service
public class PacienteService {
    
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el paciente con ID: " + id));
    }
    
    public Paciente save(Paciente paciente) {
        if (pacienteRepository.existsByIdentificacion(paciente.getIdentificacion())) {
            throw new DuplicadoException("Ya existe un paciente con la identificación: " + paciente.getIdentificacion());
        }
        return pacienteRepository.save(paciente);
    }
}
```

### En Controladores
Los controladores se simplifican ya que no necesitan manejar las excepciones manualmente:

```java
@RestController
public class PacienteController {
    
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        Paciente paciente = pacienteService.findById(id); // Lanza excepción si no existe
        return ResponseEntity.ok(pacienteMapper.toDTO(paciente));
    }
}
```

## Respuestas de Error Ejemplo

### Error 404 - Recurso No Encontrado
```json
{
    "exception": 404,
    "error": "RECURSO_NO_ENCONTRADO",
    "descripcion": "No se encontró el paciente con ID: 123",
    "timestamp": "2024-01-15 10:30:45"
}
```

### Error 409 - Duplicado
```json
{
    "exception": 409,
    "error": "DUPLICADO",
    "descripcion": "Ya existe un paciente con la identificación: 12345678",
    "timestamp": "2024-01-15 10:30:45"
}
```

### Error 400 - Validación
```json
{
    "exception": 400,
    "error": "VALIDACION",
    "descripcion": "El email es obligatorio",
    "timestamp": "2024-01-15 10:30:45"
}
```

### Error 500 - Error Interno
```json
{
    "exception": 500,
    "error": "ERROR_INTERNO",
    "descripcion": "Ha ocurrido un error interno en el servidor",
    "timestamp": "2024-01-15 10:30:45"
}
```

## Beneficios

1. **Consistencia**: Todas las respuestas de error siguen el mismo formato
2. **Claridad**: Los errores son específicos y descriptivos
3. **Mantenibilidad**: Fácil de mantener y extender
4. **Logging**: Todos los errores se registran automáticamente
5. **Seguridad**: No se exponen detalles internos del sistema en producción

## Extensión del Sistema

Para agregar nuevos tipos de errores:

1. Crear una nueva excepción que extienda de `DentisysException`
2. Agregar el manejador correspondiente en `GlobalExceptionHandler`
3. Usar la nueva excepción en los servicios según sea necesario 