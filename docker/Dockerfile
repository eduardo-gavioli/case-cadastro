# Usando uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-alpine

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando o arquivo JAR gerado pelo Maven para dentro do container
COPY target/cadastro-api.jar /app/cadastro-api.jar

# Expondo a porta usada pela aplicação (Spring Boot padrão: 8080)
EXPOSE 8080

# Definindo o comando de inicialização do container
CMD ["java", "-jar", "/app/cadastro-api.jar"]