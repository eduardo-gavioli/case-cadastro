FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/cadastro-api.jar /app/cadastro-api.jar
EXPOSE 8080
CMD ["java", "-jar", "cadastro-api.jar"]