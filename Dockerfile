# First stage: Build JAR
FROM maven:3.8.6-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Second stage: Run JAR
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/MyPlateFit-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
