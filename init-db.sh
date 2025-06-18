#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "root" <<-EOSQL
    -- Crear base de datos para producción
    CREATE DATABASE dentisys_prod;
    
    -- Crear base de datos para desarrollo
    CREATE DATABASE dentisys_dev;
    
    -- Crear nueva base de datos
    CREATE DATABASE nueva_db;
    
    -- Otorgar privilegios al usuario
    GRANT ALL PRIVILEGES ON DATABASE dentisys_prod TO root;
    GRANT ALL PRIVILEGES ON DATABASE dentisys_dev TO root;
    GRANT ALL PRIVILEGES ON DATABASE nueva_db TO root;
EOSQL 