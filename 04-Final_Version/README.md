# Table of Content

- [01 - What to do](#01---what-to-do)
- [02 - Sub-task: Service registry](#02---sub-task-service-registry)
- [03 - Project configuration](#03---project-configuration)
    * [Project Structure](#structure)
    * [Running the application](#running-the-application)
      * [Manual Running](#manual-running)
      * [Automated Running](#automated-running)

## 01 - What to do

In this task you need to choose one of Service registry tool and inject it into your infrastructure.
Please, find the sample implementation here: [Eureka Example](https://www.javainuse.com/spring/cloud-gateway-eureka)

![](Readme_Assets/task.png)

## 02 - Sub-task: Service registry

1) Use Eureka Service Registry ([Service Registration and Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)).
2) All microservices should be Eureka Clients, and they must be registered with the Eureka Server (made up of the Load Balancer and the Service Registry).

## 03 - Project configuration

### Structure
1) **Eureka Service** (Netflix Eureka Discovery Service)
   * Used to register the service location (service host)
2) **Gateway Service**
   * Used to be the only entery point of the application
       * Receving the request.
       * Checking the resourse path.
       * Pickup the right service.
       * Redirect the call to that service.
3) **Resource Service**
   * Used to store the binary representation of the resource, currentlly MP3 only.
4) **ResourceMetadata Service**
    * Used to store the Metadata representation of the resource, currentlly MP3 only.

### Configuration
*  All applications having 2 different application.properties file;
  * application.properties will be running for local development or for the image building phase.
  * application-docker.properties will be running from dockerfile as startup command, which have the connection for the postgresql and the downstream docker container for internal communication between the applications and postgres inside the **dokcer network**.
* The main [docker-compose](./docker-compose.yaml) located in root uses [docker.env](./docker.env) file to recive custom variables.
* PostgreSQL will get a [build tables script](./sql/build_tables.sql) as an init scripts for the database to run when container starts up.
* Uploaded [Postman collection](Postman_Collections/) for testing.
### Dockerizing steps

> There are 2 project **resource** and **resourcemetadata** also there a docker compose file in each project
> docker-compose under src/docker you need to run it first to guruntee that the build will be success due to Hibernate
> checking during the build.

> Also if you would like to run each application locally but using Postgresql docker image.

> docker-compose which is in the root you will run it using `docker-compose --env-file docker.env up` after you build your projects first.

> Remember to stop the docker container you have used to build the image which was under **src/docker** of each resource & resourcemetadata projects.
  
### Running the application

#### Manual Running
* Before running the application you need to start Docker deamon (docker disktop).
* Run docker-compose file from resource or resourcemetadata project, both files doing the same action
  * Starting the PostgreSql
  * Starting the PgAdmin.
* Building options
  * From CMD, navigate to each project then run `mvn clean package`
  * From Idea, you can use maven plugin.
* Running the services.
   * Before running the services make sure that the Postgres container which used to build resource & resourcemetadata projects is not running, to avoid port in use error when you run the services using docker-compose.
   * Run docker-compose under the root using `docker-compose --env-file docker.env up`.
* You can use [postman application](https://www.postman.com/) to test the endpoints and to interact with the application.
* Uploaded [Postman collection](Postman_Collections/) to make easy for tesating the api. 

#### Automated Running
* Before running the application you need to start Docker deamon (docker disktop).
* Run [RunServices](./RunServices.bat) from the root folder.
  * You can read the structure of the file, it is simple.
  * The batch will run the applications in sequence.
* You can use [postman application](https://www.postman.com/) to test the endpoints and to interact with the application.
* Uploaded [Postman collection](Postman_Collections/) to make easy for tesating the api.     


 

