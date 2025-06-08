# Use Java 23 JDK to build the app
FROM --platform=linux/amd64 eclipse-temurin:23-jdk as builder

# Set working directory
WORKDIR /app

# Copy Gradle files and source code
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Make Gradle wrapper executable and build the JAR
RUN chmod +x gradlew && ./gradlew bootJar

# Use a lightweight Java 23 JRE base image for running the app
FROM --platform=linux/amd64 eclipse-temurin:23-jre

# Set working directory in the final container
WORKDIR /app

# Copy the built jar from the builder image
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the app's port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
