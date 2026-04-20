# Paso 1: Construir la aplicación con Maven y Java 8
FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

# Paso 2: Ejecutar la aplicación con Java 8 (JRE ligero)
FROM openjdk:8-jre-slim
COPY --from=build /target/api_salud-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]