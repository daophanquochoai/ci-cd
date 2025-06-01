# Stage 1: Build ứng dụng
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /build
COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Run ứng dụng
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
