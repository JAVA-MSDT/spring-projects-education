FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/eureka-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eureka.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/eureka.jar"]