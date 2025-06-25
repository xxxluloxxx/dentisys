-- Insertar datos en la tabla general
INSERT INTO general (parametro, valor, descripcion) VALUES
('NOMBRE_CLINICA', 'DentiSys', 'Nombre de la clínica dental'),
('DIRECCION_CLINICA', 'Av. Principal 123', 'Dirección de la clínica'),
('TELEFONO_CLINICA', '123456789', 'Teléfono de la clínica'),
('EMAIL_CLINICA', 'contacto@dentisys.com', 'Email de la clínica'),
('HORARIO_ATENCION', 'L-V 9:00-18:00', 'Horario de atención'),
('IVA', '12', 'Porcentaje de IVA aplicable'),
('MONEDA', 'USD', 'Moneda del sistema'),
('VERSION_SISTEMA', '1.0.0', 'Versión actual del sistema');

-- Insertar roles
INSERT INTO roles (nombre, descripcion) VALUES
('Administrador', 'Acceso total al sistema'),
('Médico', 'Gestión de pacientes y citas'),
('Asistente', 'Gestión básica de citas'),
('Contador', 'Gestión financiera'),
('Secretaria', 'Gestión de citas y pacientes');

-- Insertar permisos
INSERT INTO permisos (nombre, descripcion) VALUES
('GESTION_USUARIOS', 'Gestión completa de usuarios'),
('GESTION_PACIENTES', 'Gestión completa de pacientes'),
('GESTION_CITAS', 'Gestión completa de citas'),
('GESTION_FICHAS', 'Gestión completa de fichas médicas'),
('GESTION_PRODUCTOS', 'Gestión completa de productos'),
('GESTION_FINANZAS', 'Gestión completa de finanzas'),
('VER_REPORTES', 'Visualización de reportes'),
('GESTION_CONFIGURACION', 'Gestión de configuración del sistema');

-- Asignar permisos a roles
INSERT INTO roles_permisos (rol_id, permiso_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8),  -- Administrador
(2, 2), (2, 3), (2, 4), (2, 7),  -- Médico
(3, 2), (3, 3), (3, 7),  -- Asistente
(4, 5), (4, 6), (4, 7),  -- Contador
(5, 2), (5, 3), (5, 7);  -- Secretaria

-- Insertar menús para Administrador (rol_id = 1) basado en menuAdmin.json
INSERT INTO menu (rol_id, label, icon, to_path, parent_id, orden, estado) VALUES
-- Menú Home
(1, 'Home', NULL, NULL, NULL, 1, true),
(1, 'Dashboard', 'pi pi-fw pi-home', '/dashboard', 1, 1, true),

-- Menú PRISMA
(1, 'PRISMA', 'pi pi-fw pi-briefcase', '/pages', NULL, 2, true),
(1, 'Productos', 'pi pi-fw pi-box', '/pages/productos', 3, 1, true),
(1, 'Pacientes', 'pi pi-fw pi-user', '/pages/pacientes', 3, 2, true),
(1, 'Medicos', 'pi pi-fw pi-id-card', '/pages/medicos', 3, 3, true),
(1, 'Cuentas', 'pi pi-fw pi-money-bill', '/pages/cuentas', 3, 4, true),

-- Submenú Ficha medica dentro de PRISMA
(1, 'Ficha medica', 'pi pi-fw pi-file-edit', NULL, 3, 5, true),
(1, 'Fichas medicas', 'pi pi-fw pi-bars', '/pages/fichasMedicas', 8, 1, true),
(1, 'Ficha Crear', 'pi pi-fw pi-file-edit', '/pages/ficha-crear', 8, 2, true),

-- Submenú Proformas dentro de PRISMA
(1, 'Proformas', 'pi pi-fw pi-book', NULL, 3, 6, true),
(1, 'Proformas', 'pi pi-fw pi-bars', '/pages/proformas', 11, 1, true),
(1, 'Crear proforma', 'pi pi-fw pi-plus-circle', '/pages/proforma', 11, 2, true),

-- Submenú Agenda dentro de PRISMA
(1, 'Agenda', 'pi pi-fw pi-calendar', NULL, 3, 7, true),
(1, 'Agenda', 'pi pi-fw pi-calendar', '/pages/agenda', 14, 1, true),
(1, 'Citas', 'pi pi-fw pi-calendar-plus', '/pages/agendarCita', 14, 2, true),

-- Menús directos en PRISMA
(1, 'Categorías Financieras', 'pi pi-fw pi-tags', '/pages/categorias-financieras', 3, 8, true),
(1, 'Configuración', 'pi pi-fw pi-cog', '/pages/configuracion', 3, 9, true);


-- Insertar menús para Médico (rol_id = 2) basado en menuMedico.json
INSERT INTO menu (rol_id, label, icon, to_path, parent_id, orden, estado) VALUES
-- Menú Home
(2, 'Home', NULL, NULL, NULL, 1, true),
(2, 'Dashboard', 'pi pi-fw pi-home', '/dashboard', 19, 1, true),

-- Menú PRISMA
(2, 'PRISMA', 'pi pi-fw pi-briefcase', '/pages', NULL, 2, true),
(2, 'Pacientes', 'pi pi-fw pi-user', '/pages/pacientes', 21, 2, true),

-- Submenú Ficha medica dentro de PRISMA
(2, 'Ficha medica', 'pi pi-fw pi-book', NULL, 21, 3, true),
(2, 'Fichas medicas', 'pi pi-fw pi-bars', '/pages/fichasMedicas', 23, 1, true),
(2, 'Ficha Crear', 'pi pi-fw pi-file-edit', '/pages/ficha-crear', 23, 2, true),

-- Submenú Proformas dentro de PRISMA
(2, 'Proformas', 'pi pi-fw pi-book', NULL, 21, 4, true),
(2, 'Proformas', 'pi pi-fw pi-bars', '/pages/proformas', 26, 1, true),
(2, 'Crear proforma', 'pi pi-fw pi-plus-circle', '/pages/proforma', 26, 2, true),

-- Submenú Agenda dentro de PRISMA
(2, 'Agenda', 'pi pi-fw pi-calendar', NULL, 21, 5, true),
(2, 'Agenda', 'pi pi-fw pi-calendar', '/pages/agenda', 29, 1, true),
(2, 'Citas', 'pi pi-fw pi-calendar-plus', '/pages/agendarCita', 29, 2, true),

-- Menú directo en PRISMA
(2, 'Configuración', 'pi pi-fw pi-cog', '/pages/configuracion', 21, 6, true);

-- Insertar usuarios
INSERT INTO usuarios (numero_documento, nombre, apellido, email, password, telefono, rol_id) VALUES
('1234567890', 'Paul', 'Arias', 'jordi.arias19@gmail.com', '123', '0991234567', 1),
('0987654321', 'Fernando', 'Arias', 'fernando.arias@dentisys.com', '123', '0997654321', 2),
('1122334455', 'Romel', 'Guachizaca', 'romel.guachizaca@dentisys.com', '123', '0991112223', 2),
('5544332211', 'Ana', 'Martínez', 'ana.martinez@dentisys.com', '$2a$10$XOPbrlUPQdwdJUpSrIF6X.LbE14qsMmKGhM1A8W9E9vpo9qX9K6G', '0993334445', 3),
('6677889900', 'Pedro', 'Sánchez', 'pedro.sanchez@dentisys.com', '$2a$10$XOPbrlUPQdwdJUpSrIF6X.LbE14qsMmKGhM1A8W9E9vpo9qX9K6G', '0995556667', 4),
('0011223344', 'Laura', 'López', 'laura.lopez@dentisys.com', '$2a$10$XOPbrlUPQdwdJUpSrIF6X.LbE14qsMmKGhM1A8W9E9vpo9qX9K6G', '0997778889', 5),
('2233445566', 'Miguel', 'Ramírez', 'miguel.ramirez@dentisys.com', '$2a$10$XOPbrlUPQdwdJUpSrIF6X.LbE14qsMmKGhM1A8W9E9vpo9qX9K6G', '0998889990', 2),
('3344556677', 'Patricia', 'Vargas', 'patricia.vargas@dentisys.com', '$2a$10$XOPbrlUPQdwdJUpSrIF6X.LbE14qsMmKGhM1A8W9E9vpo9qX9K6G', '0999990001', 2),
('4455667788', 'Roberto', 'Silva', 'roberto.silva@dentisys.com', '$2a$10$XOPbrlUPQdwdJUpSrIF6X.LbE14qsMmKGhM1A8W9E9vpo9qX9K6G', '0990001112', 3),
('5566778899', 'Carmen', 'Rojas', 'carmen.rojas@dentisys.com', '$2a$10$XOPbrlUPQdwdJUpSrIF6X.LbE14qsMmKGhM1A8W9E9vpo9qX9K6G', '0991112223', 4),
('7788990011', 'Fernando', 'Mendoza', 'fernando.mendoza@dentisys.com', '$2a$10$XOPbrlUPQdwdJUpSrIF6X.LbE14qsMmKGhM1A8W9E9vpo9qX9K6G', '0992223334', 5);

-- Insertar médicos
INSERT INTO medicos (usuario_id, especialidad) VALUES
(2, 'Ortodoncista'),
(3, 'Endodoncista'),
(4, 'Cirujano Dental'),
(5, 'Periodoncista'),
(6, 'Odontopediatra'),
(7, 'Cirujano Maxilofacial'),
(8, 'Implantólogo'),
(9, 'Endodoncista'),
(10, 'Ortodoncista'),
(11, 'Periodoncista');

-- Insertar pacientes
INSERT INTO pacientes (identificacion, nombre, apellido, fecha_nacimiento, genero, telefono, email, direccion) VALUES
('1234567890', 'Roberto', 'Pérez', '1990-05-15', 'M', '0991234567', 'roberto.perez@email.com', 'Av. Principal 123'),
('0987654321', 'Ana', 'García', '1985-08-20', 'F', '0997654321', 'ana.garcia@email.com', 'Calle 10 456'),
('1122334456', 'Carlos', 'López', '1995-03-10', 'M', '0991112223', 'carlos.lopez@email.com', 'Av. Central 789'),
('5544332211', 'María', 'Rodríguez', '1988-12-25', 'F', '0993334445', 'maria.rodriguez@email.com', 'Calle 5 321'),
('6677889901', 'Juan', 'Martínez', '1992-07-30', 'M', '0995556667', 'juan.martinez@email.com', 'Av. Norte 654'),
('0011223345', 'Laura', 'Sánchez', '1998-01-15', 'F', '0997778889', 'laura.sanchez@email.com', 'Calle 15 987'),
('5566778900', 'Pedro', 'González', '1993-11-20', 'M', '0999990001', 'pedro.gonzalez@email.com', 'Av. Sur 147'),
('2233445567', 'Carmen', 'Flores', '1997-04-05', 'F', '0991112223', 'carmen.flores@email.com', 'Calle 20 258'),
('7788990012', 'Diego', 'Ruiz', '1991-09-12', 'M', '0993334445', 'diego.ruiz@email.com', 'Av. Este 369'),
('4455667789', 'Sofia', 'Torres', '1996-06-28', 'F', '0995556667', 'sofia.torres@email.com', 'Calle 25 741'),
('1122334457', 'Ricardo', 'Mendoza', '1994-02-28', 'M', '0991112223', 'ricardo.mendoza@email.com', 'Av. Los Pinos 456'),
('2233445568', 'Isabel', 'Castro', '1991-11-15', 'F', '0992223334', 'isabel.castro@email.com', 'Calle 30 789'),
('3344556678', 'Francisco', 'Núñez', '1989-07-22', 'M', '0993334445', 'francisco.nunez@email.com', 'Av. Las Flores 123'),
('4455667790', 'Diana', 'Paredes', '1996-04-10', 'F', '0994445556', 'diana.paredes@email.com', 'Calle 35 456'),
('5566778901', 'Eduardo', 'Vega', '1993-09-05', 'M', '0995556667', 'eduardo.vega@email.com', 'Av. Los Álamos 789'),
('6677889902', 'María', 'Herrera', '1997-12-20', 'F', '0996667778', 'maria.herrera@email.com', 'Calle 40 321'),
('7788990013', 'José', 'Ramos', '1990-03-15', 'M', '0997778889', 'jose.ramos@email.com', 'Av. Las Palmas 654'),
('8899001122', 'Ana', 'Morales', '1995-08-30', 'F', '0998889990', 'ana.morales@email.com', 'Calle 45 987'),
('9900112233', 'Carlos', 'Guzmán', '1992-01-25', 'M', '0999990001', 'carlos.guzman@email.com', 'Av. Los Robles 147'),
('0011223346', 'Laura', 'Soto', '1998-06-12', 'F', '0990001112', 'laura.soto@email.com', 'Calle 50 258');

-- Insertar productos
INSERT INTO productos (nombre, descripcion, precio, categoria) VALUES
('Limpieza Dental', 'Limpieza dental profesional', 50.00, 'Servicios'),
('Blanqueamiento Dental', 'Procedimiento de blanqueamiento dental', 150.00, 'Servicios'),
('Ortodoncia', 'Tratamiento de ortodoncia básico', 2000.00, 'Servicios'),
('Implante Dental', 'Implante dental unitario', 1500.00, 'Servicios'),
('Caries', 'Tratamiento de caries', 100.00, 'Servicios'),
('Cepillo Dental', 'Cepillo dental profesional', 15.00, 'Productos'),
('Pasta Dental', 'Pasta dental profesional', 12.00, 'Productos'),
('Hilo Dental', 'Hilo dental premium', 8.00, 'Productos'),
('Enjuague Bucal', 'Enjuague bucal antiséptico', 20.00, 'Productos'),
('Protector Bucal', 'Protector bucal deportivo', 25.00, 'Productos'),
('Corona Dental', 'Corona dental de porcelana', 800.00, 'Servicios'),
('Puente Dental', 'Puente dental fijo', 1200.00, 'Servicios'),
('Extracción Dental', 'Extracción de diente', 150.00, 'Servicios'),
('Radiografía Dental', 'Radiografía panorámica', 80.00, 'Servicios'),
('Consulta Dental', 'Consulta dental general', 50.00, 'Servicios'),
('Kit Dental', 'Kit completo de higiene dental', 45.00, 'Productos'),
('Cera Dental', 'Cera para ortodoncia', 10.00, 'Productos'),
('Gel Dental', 'Gel dental para sensibilidad', 25.00, 'Productos'),
('Cepillo Eléctrico', 'Cepillo dental eléctrico', 120.00, 'Productos'),
('Irrigador Dental', 'Irrigador dental profesional', 150.00, 'Productos');

-- Insertar categorías
INSERT INTO categorias (nombre, descripcion, ingreso) VALUES
('Ingresos', 'Ingresos por servicios y productos', true),
('Gastos Operativos', 'Gastos de operación diaria', false),
('Gastos de Personal', 'Gastos relacionados con el personal', false),
('Gastos de Equipamiento', 'Gastos en equipos y materiales', false),
('Otros Gastos', 'Otros gastos varios', false);

-- Insertar proformas
INSERT INTO proformas (paciente_id, medico_id, subtotal, iva, descuento, total, estado, observaciones) VALUES
(1, 1, 250.00, 15.00, 0.00, 265.00, 'PENDIENTE', 'Tratamiento de limpieza dental'),
(2, 2, 150.00, 0.00, 7.50, 142.50, 'PAGADO', 'Blanqueamiento dental'),
(3, 3, 2000.00, 15.00, 0.00, 2015.00, 'PENDIENTE', 'Inicio de ortodoncia'),
(4, 4, 1500.00, 0.00, 75.00, 1425.00, 'PAGADO', 'Implante dental'),
(5, 5, 100.00, 15.00, 0.00, 115.00, 'PENDIENTE', 'Tratamiento de caries'),
(6, 6, 800.00, 0.00, 40.00, 760.00, 'PAGADO', 'Corona dental'),
(7, 7, 1200.00, 15.00, 0.00, 1215.00, 'PENDIENTE', 'Puente dental'),
(8, 8, 150.00, 0.00, 7.50, 142.50, 'PAGADO', 'Extracción dental'),
(9, 9, 80.00, 15.00, 0.00, 95.00, 'PENDIENTE', 'Radiografía dental'),
(10, 10, 50.00, 0.00, 2.50, 47.50, 'PAGADO', 'Consulta dental'),
(11, 1, 250.00, 15.00, 0.00, 265.00, 'PENDIENTE', 'Limpieza dental'),
(12, 2, 150.00, 0.00, 7.50, 142.50, 'PAGADO', 'Blanqueamiento dental'),
(13, 3, 2000.00, 15.00, 0.00, 2015.00, 'PENDIENTE', 'Ortodoncia'),
(14, 4, 1500.00, 0.00, 75.00, 1425.00, 'PAGADO', 'Implante dental'),
(15, 5, 100.00, 15.00, 0.00, 115.00, 'PENDIENTE', 'Tratamiento de caries');

-- Insertar detalles de proforma
INSERT INTO detalles_proforma (proforma_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 1, 50.00, 50.00),
(1, 6, 2, 15.00, 30.00),
(1, 7, 2, 12.00, 24.00),
(2, 2, 1, 150.00, 150.00),
(3, 3, 1, 2000.00, 2000.00),
(4, 4, 1, 1500.00, 1500.00),
(5, 5, 1, 100.00, 100.00),
(6, 11, 1, 800.00, 800.00),
(7, 12, 1, 1200.00, 1200.00),
(8, 13, 1, 150.00, 150.00),
(9, 14, 1, 80.00, 80.00),
(10, 15, 1, 50.00, 50.00),
(11, 1, 1, 50.00, 50.00),
(11, 16, 1, 45.00, 45.00),
(12, 2, 1, 150.00, 150.00),
(13, 3, 1, 2000.00, 2000.00),
(14, 4, 1, 1500.00, 1500.00);

-- Insertar cobranzas
INSERT INTO cobranzas (proforma_id, monto, fecha_pago, metodo_pago, estado, observaciones) VALUES
(2, 168.00, '2024-03-15', 'EFECTIVO', 'COMPLETADO', 'Pago completo'),
(4, 1680.00, '2024-03-16', 'TARJETA', 'COMPLETADO', 'Pago con tarjeta de crédito'),
(1, 140.00, '2024-03-17', 'TRANSFERENCIA', 'PARCIAL', 'Primer pago'),
(3, 1000.00, '2024-03-18', 'EFECTIVO', 'PARCIAL', 'Pago inicial'),
(5, 56.00, '2024-03-19', 'TARJETA', 'COMPLETADO', 'Pago completo'),
(6, 896.00, '2024-03-20', 'TARJETA', 'COMPLETADO', 'Pago completo'),
(8, 168.00, '2024-03-21', 'EFECTIVO', 'COMPLETADO', 'Pago completo'),
(10, 56.00, '2024-03-22', 'TRANSFERENCIA', 'COMPLETADO', 'Pago completo'),
(12, 168.00, '2024-03-23', 'TARJETA', 'COMPLETADO', 'Pago completo'),
(14, 1680.00, '2024-03-24', 'EFECTIVO', 'COMPLETADO', 'Pago completo'),
(7, 672.00, '2024-03-25', 'TRANSFERENCIA', 'PARCIAL', 'Primer pago'),
(9, 44.80, '2024-03-26', 'TARJETA', 'PARCIAL', 'Pago inicial'),
(11, 140.00, '2024-03-27', 'EFECTIVO', 'PARCIAL', 'Primer pago'),
(13, 1120.00, '2024-03-28', 'TARJETA', 'PARCIAL', 'Pago inicial'),
(15, 56.00, '2024-03-29', 'EFECTIVO', 'PARCIAL', 'Primer pago');

-- Insertar cuentas
INSERT INTO cuentas (categoria_id, cobranza_id, monto, fecha_movimiento, descripcion) VALUES
(1, 1, 168.00, '2024-03-15', 'Pago de blanqueamiento dental'),
(1, 2, 1680.00, '2024-03-16', 'Pago de implante dental'),
(2, NULL, 500.00, '2024-03-17', 'Compra de materiales'),
(3, NULL, 3000.00, '2024-03-18', 'Pago de salarios'),
(1, 3, 140.00, '2024-03-19', 'Pago parcial de limpieza dental'),
(1, 6, 896.00, '2024-03-20', 'Pago de corona dental'),
(1, 7, 168.00, '2024-03-21', 'Pago de extracción dental'),
(1, 8, 56.00, '2024-03-22', 'Pago de consulta dental'),
(2, NULL, 800.00, '2024-03-23', 'Compra de equipos dentales'),
(3, NULL, 2500.00, '2024-03-24', 'Pago de salarios'),
(1, 10, 168.00, '2024-03-25', 'Pago de blanqueamiento dental'),
(2, NULL, 1200.00, '2024-03-26', 'Compra de materiales dentales'),
(1, 12, 44.80, '2024-03-27', 'Pago parcial de radiografía');

-- Insertar fichas médicas
INSERT INTO fichas_medicas (paciente_id, medico_id, datos) VALUES
(1, 1, '{"antecedentes": "Hipertensión", "alergias": "Penicilina", "medicamentos_actuales": "Ninguno", "observaciones": "Paciente con buena salud bucal"}'),
(2, 2, '{"antecedentes": "Diabetes", "alergias": "Ninguna", "medicamentos_actuales": "Insulina", "observaciones": "Requiere control especial"}'),
(3, 3, '{"antecedentes": "Asma", "alergias": "Ninguna", "medicamentos_actuales": "Inhalador", "observaciones": "Paciente con buena respuesta al tratamiento"}'),
(4, 4, '{"antecedentes": "Ninguno", "alergias": "Ninguna", "medicamentos_actuales": "Ninguno", "observaciones": "Paciente saludable"}'),
(5, 5, '{"antecedentes": "Alergias", "alergias": "Penicilina", "medicamentos_actuales": "Antihistamínicos", "observaciones": "Requiere precaución con medicamentos"}');

-- Insertar procedimientos
INSERT INTO procedimientos (ficha_id, procedimiento, observaciones) VALUES
(1, 'Limpieza dental profunda', 'Se realizó limpieza dental completa con eliminación de sarro'),
(1, 'Aplicación de flúor', 'Se aplicó flúor para fortalecer el esmalte dental'),
(2, 'Tratamiento de caries', 'Se realizó obturación en molar superior derecho'),
(3, 'Ortodoncia inicial', 'Se colocaron brackets en arcada superior'),
(4, 'Implante dental', 'Se realizó implante en molar inferior izquierdo'),
(5, 'Extracción dental', 'Se extrajo molar con caries profunda');

-- Insertar citas
INSERT INTO citas (paciente_id, medico_id, fecha_cita, hora_cita, estado, observaciones) VALUES
(1, 1, '2024-03-20', '09:00:00', 'CONFIRMADA', 'Primera consulta de rutina.'),
(2, 2, '2024-03-20', '09:00:00', 'CONFIRMADA', 'Seguimiento de ortodoncia'),
(3, 3, '2024-03-21', '09:00:00', 'PENDIENTE', 'Consulta de emergencia'),
(4, 4, '2024-03-21', '10:00:00', 'CONFIRMADA', 'Revisión post-operatoria'),
(5, 5, '2024-03-22', '09:00:00', 'PENDIENTE', 'Primera consulta'),
(6, 6, '2024-03-22', '10:00:00', 'CONFIRMADA', 'Seguimiento de implante'),
(7, 1, '2024-03-20', '09:30:00', 'CANCELADA', 'Paciente canceló'),
(8, 2, '2024-03-20', '09:30:00', 'CONFIRMADA', 'Limpieza dental'),
(9, 3, '2024-03-21', '09:30:00', 'PENDIENTE', 'Consulta de rutina'),
(10, 4, '2024-03-21', '10:30:00', 'CONFIRMADA', 'Revisión de ortodoncia');


