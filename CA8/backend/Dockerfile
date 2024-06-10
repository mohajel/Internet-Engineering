FROM openjdk:17-jdk-slim

# Copy the entire utils directory to the Docker image
COPY src/main/java/com/github/mohajel/IE/CA8/utils/inputForInit.txt /app/src/main/java/com/github/mohajel/IE/CA8/utils/inputForInit.txt

# Add the CA8.jar file
ADD target/CA8.jar /app/CA8.jar

# Set the working directory
WORKDIR /app

# Define the entrypoint
ENTRYPOINT ["java" , "-jar" , "CA8.jar"]

# Expose the port
EXPOSE 8083