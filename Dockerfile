FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /build

# Copiar arquivos necessários e construir o aplicativo em uma única camada
COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /apimuralvagas

# Copiar o JAR construído para a etapa final
COPY --from=build /build/target/apimuralvagas-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "apimuralvagas-0.0.1-SNAPSHOT.jar"]
