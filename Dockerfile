# Stage 1: Build the application
FROM gradle:7.4.1-jdk17 AS build
WORKDIR /home/gradle/project
COPY . .
RUN gradle clean build

# Stage 2: Create runtime image
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]