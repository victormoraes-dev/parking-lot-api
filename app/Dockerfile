FROM amazoncorretto:17.0.11-al2-generic as builder
WORKDIR /workspace
COPY . .
RUN ./mvnw clean package

FROM amazoncorretto:17.0.11-al2-generic
WORKDIR /app
COPY --from=builder /workspace/target/*.jar /app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]
