FROM amazoncorretto:21.0.2-alpine as builder
WORKDIR /workspace
COPY . .
RUN ./mvnw clean package

FROM amazoncorretto:21.0.2-alpine
WORKDIR /app
COPY --from=builder /workspace/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
