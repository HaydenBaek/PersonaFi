# 1. using java 21 for building the project
FROM eclipse-temurin:21-jdk AS build

# 2. set the working directory
WORKDIR /app

# 3. copy everything into the container
COPY . .

# 4. build the spring boot app (using maven wrapper)
RUN ./mvnw clean package -DskipTests


# 5. using java 21 again, but just for running now
FROM eclipse-temurin:21-jdk

# 6. set workdir again
WORKDIR /app

# 7. copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# 8. open port 8080 (since backend runs here)
EXPOSE 8080

# 9. run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]