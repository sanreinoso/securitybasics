FROM maven:3.9-eclipse-temurin-21-jammy As build
LABEL authors="Santi Reinoso"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM maven:3.9-eclipse-temurin-21-jammy
WORKDIR /app
COPY --from=build /app/target/springsec.oauth2-0.0.1-SNAPSHOT.jar ./SpringSec-SocialMedia.jar
EXPOSE 8080
CMD ["java", "-jar", "./SpringSec-SocialMedia.jar"]