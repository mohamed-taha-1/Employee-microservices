# Use the official OpenJDK 17 image from Docker Hub as a base image
FROM openjdk:17-jdk-slim as build

LABEL authors="Mohamed.Taha"
# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build artifact (JAR file) from the local machine
COPY target/EmployeeService-0.0.1-SNAPSHOT.jar myapp.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]

# Expose the port your application is running on
EXPOSE 8080
