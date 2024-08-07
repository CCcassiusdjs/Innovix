# Use a base image from the official Gradle image with JDK 20
FROM gradle:jdk17 as builder

# Copy the project files to the container
COPY . /home/gradle/src

# Set the working directory to the project directory
WORKDIR /home/gradle/src

# Compile the application
RUN gradle build --no-daemon

# Use a smaller base image for the runtime environment
FROM openjdk:17

# Copy the compiled JAR from the builder stage to the /app directory in the container
COPY --from=builder /home/gradle/src/build/libs/innovix-1.0.jar /app/innovix-1.0.jar

# Set the working directory to /app
WORKDIR /app

# Command to execute the JAR with debug enabled
CMD ["java", "-jar", "innovix-1.0.jar", "--debug"]
