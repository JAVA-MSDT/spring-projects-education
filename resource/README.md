# Resources
## Table of Content
1) [Description](#1---description)
2) [Project Configuration](#2---project-configuration)
    * [1 - Libraries](#1---libraries)
    * [2 - Building & Running](#2---building-&-running)

## 1 - Description 
Application to manage resources such as files, audio, videos, and etc.

## 2 - Project Configuration
### 1 - Libraries
* [Java](https://www.openlogic.com/openjdk-downloads) V17 - [Direct download](https://builds.openlogic.com/downloadJDK/openlogic-openjdk/17.0.6+10/openlogic-openjdk-17.0.6+10-windows-x64.zip)
* [Maven](https://maven.apache.org/) 
* [SpringBoot](https://spring.io/projects/spring-boot) V3.1.1, [Spring Initializer](https://start.spring.io/)
    * [What is Spring Boot under the hood (In 10 Minutes)](https://youtu.be/7zOvIgcq478) 
* [PostgreSQL](https://www.postgresql.org/) - [DockerImage](https://hub.docker.com/_/postgres).
* [ProjectLombok](https://projectlombok.org/) - class members generator.
* [Apache tika](https://tika.apache.org/) - Metadata extraction.

### 2 - Building & Running.
* [Clone the project](https://github.com/JAVA-MSDT/resources.git).
* Import it in your favorite IDE.
* **Build using maven**
  *  Use maven plugin in your ide, clean install.
  *  Or use CMD from the project root where pom.xml file resides command `mvn clean instal`
* Run docker-compose to make sure that the docker postgres container started fine.
  * Make sure that docker deamon (**docker desktop**) running before running docker-compose file.
* Run the application form the main class.
* Use the [postman collections](PostmanCollections/) for testing, uploaded in the project.
