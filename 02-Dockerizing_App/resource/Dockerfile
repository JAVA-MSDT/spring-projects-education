FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} source.jar
# EXPOSE 8000
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/source.jar"]