# Resource Processor Service

# Table of Content
1) [Description](#01---description)
2) [Project Configuration](#02---project-configuration)
    * [1 - Libraries](#01---libraries)
    * [2 - Building & Running](#02---building-and-running)
      
## 01 - Description

* Resource Processor service used to perform the following actions;
  * CRUD operations for resources mmetadata such as MP3 file.
  * Storing the metadata in the service DB
## 02 - Project Configuration
### 01 - Libraries
* [Java](https://www.openlogic.com/openjdk-downloads) V17 - [Direct download](https://builds.openlogic.com/downloadJDK/openlogic-openjdk/17.0.6+10/openlogic-openjdk-17.0.6+10-windows-x64.zip)
* [Maven](https://maven.apache.org/) 
* [SpringBoot](https://spring.io/projects/spring-boot) V3.1.1, [Spring Initializer](https://start.spring.io/)
    * [What is Spring Boot under the hood (In 10 Minutes)](https://youtu.be/7zOvIgcq478) 
* [PostgreSQL](https://www.postgresql.org/) - [DockerImage](https://hub.docker.com/_/postgres).
  * If you do not have Docker installed, you can use a local instance of Postgress.
  * Check [application.yaml](src/main/resources/application.yaml) file for more details of postgresql and other properties for application running.
* [ProjectLombok](https://projectlombok.org/) - class members generator.
* [Apache tika](https://tika.apache.org/) - Metadata extraction.

### 02 - Building and Running.
* **Build using maven**
  *  Use maven plugin in your ide, clean install.
  *  Or use CMD from the project root where pom.xml file resides command `mvn clean instal`
* Run [docker-compose](src/docker) to make sure that the docker postgres container started fine.
  * Make sure that docker deamon (**docker desktop**) running before running docker-compose file.
* Run the application form the main class.
* Use the [postman collections](Postman_collections) for testing, uploaded in the project.
