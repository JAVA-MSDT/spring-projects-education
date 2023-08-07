# Resource Service

# Table of Content
1) [Description](#01---description)
2) [Project Configuration](#2---project-configuration)
    * [1 - Libraries](#01---libraries)
    * [2 - Building & Running](#2---building-and-running)
      
## 01 - Description

* Resource service used to perform the following actions;
  * CRUD operations for file resources regardless of the file type.
  * Storing the file in AWS S3 storage, then stroing the url in the service DB
## 02 - Project Configuration
### 01 - Libraries
* [Java](https://www.openlogic.com/openjdk-downloads) V17 - [Direct download](https://builds.openlogic.com/downloadJDK/openlogic-openjdk/17.0.6+10/openlogic-openjdk-17.0.6+10-windows-x64.zip)
* [Maven](https://maven.apache.org/) 
* [SpringBoot](https://spring.io/projects/spring-boot) V3.1.1, [Spring Initializer](https://start.spring.io/)
    * [What is Spring Boot under the hood (In 10 Minutes)](https://youtu.be/7zOvIgcq478) 
* [PostgreSQL](https://www.postgresql.org/) - [DockerImage](https://hub.docker.com/_/postgres).
  * If you do not have Docker installed, you can use a local instance of Postgress.
  * Check [application.yaml](resource/src/main/resources/application.yaml) file for more details of postgresql and other properties for application running.
* [ProjectLombok](https://projectlombok.org/) - class members generator.
* [Localstack](https://github.com/localstack/localstack) - AWS S3 storage .
  * provided ready to run [localstack](resource/localstack) folder contains explaination of how to setup and run localstack locally or using docker.
* aws spring cloud starter for S3
* aws sdk.

### 02 - Building and Running.
* **Build using maven**
  *  Use maven plugin in your ide, clean install.
  *  Or use CMD from the project root where pom.xml file resides command `mvn clean instal`
* Run docker-compose to make sure that the docker postgres container started fine.
  * Make sure that docker deamon (**docker desktop**) running before running docker-compose file.
* Run the application form the main class.
* Use the [postman collections](resource/Postman_Collections) for testing, uploaded in the project.
