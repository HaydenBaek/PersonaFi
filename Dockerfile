FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy the backend folder contents into the image
COPY backend/ .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
