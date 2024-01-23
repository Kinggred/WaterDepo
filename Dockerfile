FROM gradle:8.5.0-jdk17 AS builder

# Copy the entire project into the Docker image
COPY . /home/gradle/src

# Set the working directory to the project directory
WORKDIR /home/gradle/src

# Execute the Gradle wrapper script to resolve dependencies and compile the project
RUN gradle assemble

# Create a new, slimmed-down image based on adoptopenjdk:17-jre-hotspot
FROM eclipse-temurin:17-jdk-jammy

# Copy the compiled JAR file into the new image
COPY --from=builder /home/gradle/src/build/libs/*.jar /app.jar

# Specify the command to run the application
CMD ["java", "-jar", "/app.jar"]
