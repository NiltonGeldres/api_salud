FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

# Paso 2: Ejecutar con Amazon Corretto 8 (Mucho más estable)
FROM amazoncorretto:8-alpine
COPY --from=build /target/api_salud-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]