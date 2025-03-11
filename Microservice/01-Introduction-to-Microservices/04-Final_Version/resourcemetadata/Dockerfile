FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/resourcemetadata-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} resourcemetadata.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/resourcemetadata.jar"]