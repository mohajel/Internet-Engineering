# Stage 1: Build
#FROM maven:3.8.5-openjdk-17 AS MAVEN
#MAINTAINER BALOOT_DEVELOPER
#COPY pom.xml /build/

FROM eclipse-temurin:19.0.2_7-jre-jammy

# Copy the entire utils directory to the Docker image
COPY src/main/java/com/github/mohajel/IE/CA8/utils /app/com/github/mohajel/IE/CA8/utils

# Add the CA8.jar file
ADD target/CA8.jar /app/CA8.jar

# Set the working directory
WORKDIR /app

# Define the entrypoint
ENTRYPOINT ["java" , "-jar" , "CA8.jar"]

# Expose the port
EXPOSE 8083
EXPOSE 3306
