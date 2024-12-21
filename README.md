# Spring Boot Application

This is a Spring Boot application that can be built and run locally using Maven, as well as Dockerized for container deployment.

# OpenAPI Documentation

- Use the `employee.yaml` file located at `src/main/resources/static/open-api-contracts/employee.yaml` and import it into Postman as a collection. Once imported, you can easily interact with the application's endpoints.


## Prerequisites

Before following the steps, ensure you have the following installed on your machine:

- **Java 17 JDK** (from Oracle)
- **Maven 3**
- **Docker Desktop** (for building and running Docker images)

---

## Application Run Steps

To run the application locally, follow these steps:

### 1. **Pull the Latest Code**
   - First, ensure that you have the latest code from the repository:
   ```bash
   git pull
   ```

### 2. **Build the Application**
   - After pulling the latest code, run the Maven build to compile and package the Spring Boot application:
   ```
   mvn clean install
   ```

### 3. **Run the Application**
   - Once the application has been successfully built, run the Spring Boot application with:
   ```bash
   mvn spring-boot:run
   ```
   By default, the application will run on port `8080`. You can access the application at `http://localhost:8080`.

---

## Docker Image Steps

To Dockerize the Spring Boot application, follow these steps:

### 1. **Build the Docker Image**
   - To build the Docker image, run the following command in the project root directory (where your Dockerfile is located):
   ```bash
   docker build -t ddddd1234/backend:{version-name} .
   ```

### 2. **Run the Docker Container**
   - After building the Docker image, run it in a container with the following command:
   ```bash
   docker run -p 8080:8080 ddddd1234/backend:{version-name}
   ```

### 3. **Push the Docker Image to Docker Hub**
   - If you need to push the Docker image to Docker Hub, use the following command:
   ```bash
   docker push ddddd1234/backend:{version-name}
   ```

### my docker image   [ my backend application ] 
```bash
 docker pull ddddd1234/backend
```



