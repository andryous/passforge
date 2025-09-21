# Stage 1: Build the application using Maven
# Defines the build environment using the Eclipse Temurin JDK 21 on Jammy (a stable Ubuntu LTS).
# A multi-stage build is used to keep the final image small and secure.
FROM eclipse-temurin:21-jdk-jammy as builder

# Sets the working directory inside the container.
WORKDIR /app

# Copy the Maven wrapper and the project configuration file first.
# This leverages Docker's layer caching. If pom.xml remains unchanged,
# dependencies will not be re-downloaded in subsequent builds, speeding up the process.
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Download all the project dependencies.
RUN ./mvnw dependency:go-offline

# Copy the application's source code.
COPY src ./src

# Build the application, creating the executable .jar file.
# Skips running tests, as they are not needed for the final build artifact.
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final, lightweight production image
# Uses a JRE (Java Runtime Environment) image, which is smaller than a JDK image,
# as it's only needed to run the application, not to build it.
FROM eclipse-temurin:21-jre-jammy

# Sets the working directory for the final image.
WORKDIR /app

# Exposes the port the application will listen on inside the container.
EXPOSE 8080

# Copy the built .jar file from the 'builder' stage.
# The artifact name must match the one generated in the 'target' directory.
COPY --from=builder /app/target/passforge-0.0.1-SNAPSHOT.jar app.jar

# Defines the command to run the application when the container starts.
ENTRYPOINT ["java", "-jar", "app.jar"]