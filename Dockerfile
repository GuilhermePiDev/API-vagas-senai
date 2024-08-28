FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /build

# Copiar apenas o arquivo de dependências
COPY pom.xml .

# Baixar as dependências
RUN mvn dependency:go-offline

# Copiar o restante do código
COPY src ./src

# Construir o aplicativo
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-alpine
WORKDIR /apimuralvagas

# Copiar o JAR construído para a etapa final
COPY --from=build /build/target/apimuralvagas-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "apimuralvagas-0.0.1-SNAPSHOT.jar"]
