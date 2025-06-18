#!/bin/bash

# Colores para los mensajes
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${YELLOW}Iniciando actualización del entorno de producción...${NC}"

# Navegar al directorio del proyecto
cd "$(dirname "$0")"

# Detener el contenedor de producción
echo -e "${GREEN}Deteniendo contenedor de producción...${NC}"
docker stop dentisys-prod
docker rm dentisys-prod

# Actualizar el código
echo -e "${GREEN}Actualizando código desde main...${NC}"
git checkout main
git pull origin main

# Reconstruir y levantar el contenedor de producción
echo -e "${GREEN}Reconstruyendo y levantando contenedor de producción...${NC}"
docker compose -f docker-compose.app.yml up -d --build dentisys-prod

# Verificar que el contenedor esté corriendo
echo -e "${GREEN}Verificando estado del contenedor...${NC}"
docker ps | grep dentisys-prod

# Verificar logs del contenedor
echo -e "${GREEN}Mostrando logs del contenedor...${NC}"
docker logs dentisys-prod

echo -e "${GREEN}¡Actualización completada!${NC}"
echo -e "La aplicación está disponible en:"
echo -e "Producción: http://93.127.217.21:8080" 