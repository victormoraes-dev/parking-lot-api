FROM amazoncorretto:21.0.2-alpine
WORKDIR /workspace/app
COPY . .
RUN ./mvnw clean package
COPY target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]