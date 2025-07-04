-- Tabla General para Parámetros del Sistema
CREATE TABLE general (
    id SERIAL PRIMARY KEY,
    parametro VARCHAR(50) UNIQUE NOT NULL,
    valor VARCHAR(255) NOT NULL,
    descripcion TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Roles
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    descripcion TEXT,
    estado BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Permisos
CREATE TABLE permisos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    descripcion TEXT,
    estado BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para relacionar roles con permisos
CREATE TABLE roles_permisos (
    id SERIAL PRIMARY KEY,
    rol_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    permiso_id INTEGER NOT NULL REFERENCES permisos(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(rol_id, permiso_id)
);

-- Tabla de Menú
CREATE TABLE menu (
    id SERIAL PRIMARY KEY,
    rol_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    label VARCHAR(100) NOT NULL,
    icon VARCHAR(100),
    to_path VARCHAR(200),
    parent_id INTEGER REFERENCES menu(id) ON DELETE CASCADE,
    orden INTEGER DEFAULT 0,
    estado BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Usuarios
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    numero_documento VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    estado BOOLEAN DEFAULT true,
    ultimo_acceso TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rol_id INTEGER REFERENCES roles(id) ON DELETE SET NULL
);

-- Tabla de Médicos
CREATE TABLE medicos (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    especialidad VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(usuario_id)
);

-- Tabla de Pacientes
CREATE TABLE pacientes (
    id SERIAL PRIMARY KEY,
    identificacion VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    genero VARCHAR(10) NOT NULL CHECK (genero IN ('M', 'F', 'O')),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Citas
CREATE TABLE citas (
    id SERIAL PRIMARY KEY,
    paciente_id INTEGER NOT NULL REFERENCES pacientes(id) ON DELETE CASCADE,
    medico_id INTEGER NOT NULL REFERENCES medicos(id) ON DELETE CASCADE,
    fecha_cita DATE NOT NULL,
    hora_cita TIME NOT NULL,
    hora_cita_fin TIME NOT NULL,
    estado VARCHAR(20) NOT NULL,
    observaciones TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Fichas Médicas
CREATE TABLE fichas_medicas (
    id SERIAL PRIMARY KEY,
    paciente_id INTEGER NOT NULL REFERENCES pacientes(id) ON DELETE CASCADE,
    medico_id INTEGER NOT NULL REFERENCES medicos(id) ON DELETE CASCADE,
    datos JSONB NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Procedimientos
CREATE TABLE procedimientos (
    id SERIAL PRIMARY KEY,
    ficha_id INTEGER NOT NULL REFERENCES fichas_medicas(id) ON DELETE CASCADE,
    procedimiento VARCHAR(255) NOT NULL,
    observaciones TEXT,
    fecha DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Productos/Servicios
CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
    categoria VARCHAR(50) NOT NULL,
    estado BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Proformas
CREATE TABLE proformas (
    id SERIAL PRIMARY KEY,
    paciente_id INTEGER NOT NULL REFERENCES pacientes(id) ON DELETE CASCADE,
    medico_id INTEGER NOT NULL REFERENCES medicos(id) ON DELETE CASCADE,
    subtotal DECIMAL(10,2) NOT NULL CHECK (subtotal >= 0),
    iva DECIMAL(10,2) NOT NULL CHECK (iva >= 0),
    descuento DECIMAL(10,2) NOT NULL DEFAULT 0 CHECK (descuento >= 0),
    total DECIMAL(10,2) NOT NULL CHECK (total >= 0),
    estado VARCHAR(20) NOT NULL,
    observaciones TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Detalles de Proforma
CREATE TABLE detalles_proforma (
    id SERIAL PRIMARY KEY,
    proforma_id INTEGER NOT NULL REFERENCES proformas(id) ON DELETE CASCADE,
    producto_id INTEGER NOT NULL REFERENCES productos(id) ON DELETE CASCADE,
    cantidad INTEGER NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10,2) NOT NULL CHECK (precio_unitario >= 0),
    subtotal DECIMAL(10,2) NOT NULL CHECK (subtotal >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de imágenes
CREATE TABLE imagenes_fichas (
    id SERIAL PRIMARY KEY,
    ficha_id INTEGER NOT NULL REFERENCES fichas_medicas(id) ON DELETE CASCADE,
    nombre VARCHAR(255) NOT NULL,
    imagen BYTEA NOT NULL,
    descripcion TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Categorías de Ingresos/Egresos
CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    ingreso BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Cobranzas
CREATE TABLE cobranzas (
    id SERIAL PRIMARY KEY,
    proforma_id INTEGER NOT NULL REFERENCES proformas(id) ON DELETE CASCADE,
    monto DECIMAL(10,2) NOT NULL CHECK (monto >= 0),
    fecha_pago DATE NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    observaciones TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Cuentas (Ingresos/Egresos)
CREATE TABLE cuentas (
    id SERIAL PRIMARY KEY,
    categoria_id INTEGER NOT NULL REFERENCES categorias(id) ON DELETE RESTRICT,
    cobranza_id INTEGER REFERENCES cobranzas(id) ON DELETE SET NULL,
    medico_id INTEGER REFERENCES medicos(id) ON DELETE SET NULL,
    monto DECIMAL(10,2) NOT NULL CHECK (monto >= 0),
    fecha_movimiento DATE NOT NULL,
    descripcion TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para mejorar el rendimiento
CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_usuarios_numero_documento ON usuarios(numero_documento);
CREATE INDEX idx_pacientes_identificacion ON pacientes(identificacion);
CREATE INDEX idx_citas_fecha_cita ON citas(fecha_cita);
CREATE INDEX idx_citas_estado ON citas(estado);
CREATE INDEX idx_proformas_estado ON proformas(estado);
CREATE INDEX idx_cobranzas_fecha_pago ON cobranzas(fecha_pago);
CREATE INDEX idx_cobranzas_estado ON cobranzas(estado);
CREATE INDEX idx_cuentas_fecha ON cuentas(fecha_movimiento);
CREATE INDEX idx_cuentas_categoria ON cuentas(categoria_id);
CREATE INDEX idx_categorias_nombre ON categorias(nombre);
CREATE INDEX idx_procedimientos_ficha_id ON procedimientos(ficha_id);
CREATE INDEX idx_procedimientos_procedimiento ON procedimientos(procedimiento);
CREATE INDEX idx_menu_rol_id ON menu(rol_id);
CREATE INDEX idx_menu_parent_id ON menu(parent_id);
CREATE INDEX idx_menu_orden ON menu(orden);

-- Crear función para el trigger de updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Crear triggers para todas las tablas que tienen updated_at
CREATE TRIGGER update_general_updated_at
    BEFORE UPDATE ON general
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_medicos_updated_at
    BEFORE UPDATE ON medicos
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_pacientes_updated_at
    BEFORE UPDATE ON pacientes
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_citas_updated_at
    BEFORE UPDATE ON citas
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_fichas_medicas_updated_at
    BEFORE UPDATE ON fichas_medicas
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_productos_updated_at
    BEFORE UPDATE ON productos
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_proformas_updated_at
    BEFORE UPDATE ON proformas
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_detalles_proforma_updated_at
    BEFORE UPDATE ON detalles_proforma
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_cobranzas_updated_at
    BEFORE UPDATE ON cobranzas
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_imagenes_fichas_updated_at
    BEFORE UPDATE ON imagenes_fichas
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_categorias_updated_at
    BEFORE UPDATE ON categorias
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_cuentas_updated_at
    BEFORE UPDATE ON cuentas
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_procedimientos_updated_at
    BEFORE UPDATE ON procedimientos
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_menu_updated_at
    BEFORE UPDATE ON menu
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();
    