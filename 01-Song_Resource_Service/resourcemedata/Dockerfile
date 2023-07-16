FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sourcemetadata.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/sourcemetadata.jar"]