# Etapa de construcción
FROM eclipse-temurin:21-jdk-alpine as build

# Configurar repositorios de Alpine y actualizar
RUN apk update && \
    apk add --no-cache curl wget maven

WORKDIR /workspace/app

# Copiar solo los archivos necesarios para la dependencias primero
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Descargar dependencias (esto se cachea si el pom.xml no cambia)
RUN --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline

# Ahora copiar el código fuente
COPY src src

# Construir la aplicación
RUN --mount=type=cache,target=/root/.m2 ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jre-alpine

# Instalar herramientas necesarias para el healthcheck
RUN apk add --no-cache curl

WORKDIR /app
COPY --from=build /workspace/app/target/*.jar app.jar

EXPOSE 8081

# Configurar healthcheck usando curl en lugar de wget
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8081/actuator/health || exit 1

# Configurar variables de entorno para Java
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# Ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -Dspring.profiles.active=prod -jar app.jar"]
