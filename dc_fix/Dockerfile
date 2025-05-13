FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY ./dc_fix.jar /app/dc_fix.jar

# Expose application port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "dc_fix.jar"]
