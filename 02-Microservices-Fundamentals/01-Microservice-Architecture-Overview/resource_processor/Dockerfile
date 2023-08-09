FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/resource-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} resource.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/resource.jar"]