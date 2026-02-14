# Use OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build project using Maven (assuming pom.xml is present)
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/smart-clinic-0.0.1-SNAPSHOT.jar"]
