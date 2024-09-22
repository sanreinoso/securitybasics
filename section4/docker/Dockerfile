FROM maven:3.9-eclipse-temurin-21-jammy As build
LABEL authors="Santi Reinoso"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM java21-graalvm:local
WORKDIR /app
COPY --from=build /app/target/securitybasic-*.jar ./SecurityBasic.jar
EXPOSE 8080
CMD ["java", "-jar", "./SecurityBasic.jar"]
