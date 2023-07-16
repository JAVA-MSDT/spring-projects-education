# Table of Content

 - [01 - What to do](#01---what-to-do)
 - [02 - Sub-task 1: Docker images](#02---sub-task-1-docker-images)
 - [03 - Sub-task 2: Docker Compose file](#03---sub-task-2-docker-compose-file)
 - [04 - Running the app: Docker Compose files](#04---running-the-docker)
 - [05 - Troubleshooting](#05---troubleshooting)

## 01 - What to do

In this module you will need to adjust your services with containerization approach.

## 02 - Sub-task 1: Docker images

1) Package your applications as Docker images.
2) For each of your services:
 - Create a _Docker_ file that would contain instruction on how to package your project.
 - Build a docker image and run it, mapping an external port to verify that application can be started and respond to requests.

## 03 - Sub-task 2: Docker Compose file

1) When all applications are successfully packaged, create a _docker-compose.yml_ file that would list all applications and 3rd party dependencies to successfully start the project.
Add init scripts for the database to run when container starts up. Once you have a compose file, you can create and start your application containers with a single command: `docker-compose up`.

Please note the following:
 - Use an _.env_ file to replace all environment variables depending on the set-up.
 - For 3rd party dependencies try to use the _–alpine_ images whenever it's possible.
 - For project applications use the build property as these images are not going to be pulled from a public hub.
 - Use logical service names to cross-reference services.

Possible container options for existing resources:

 - [postgres DB](https://hub.docker.com/_/postgres)
 - [mysql db](https://hub.docker.com/_/mysql)
 - [Local stack (aws emulator)](https://hub.docker.com/r/localstack/localstack)

## 04 - Running the docker
### Configuration
* Both applications having 2 different application.properties file;
  * application.properties will be running for local development or for the image building phase.
  * application-docker.properties will be running from dockerfile as startup command, which have the connection for the postgresql and the downstream docker container for internal communication between the applications and postgres.
* The main [docker-compose](./docker-compose.yaml) located in root uses [docker.env](./docker.env) file to recive custom variables.
* PostgreSQL will get a [build tables script](./sql/build_tables.sql) as an init scripts for the database to run when container starts up.
### Dockerizing steps

> There are 2 project **resource** and **resourcemetadata** also there a docker compose file in each project
> docker-compose under src/docker you need to run it first to guruntee that the build will be success due to Hibernate
> checking during the build.
> Also if you would like to run each application locally but using Postgresql docker image.
> docker-compose which is in the root you will run it using `docker-compose --env-file docker.env up` after you have build your project, because it > has the configuration of the newtwork where postgreSQL will run first then the reource image, but remember to stop the docker container you have > used to build the image which was under **src/docker** of each project.

### Steps to successfully run the application in docker mode.
#### Manule running.

* Run docker-compose under src/docker.
    * The reason for this step is becuase during the run Hibernate will ckeck that there is a connection between the
      application and the database.
    * So this step is mendatory to build a docker image for the resource application, then later you can build a
      container from that image locally or push it to the hub.
* Package the application using maven tab in your IDEA or CMD command `mvn clean package`
* You should now find a jar file under target/
* Stop the docker container which you have run from docker-compose under src/docker.
    * This step is important to avoid the port in use conflict, especially for the postgresql.
* Run docker-compose under the root using `docker-compose --env-file docker.env up`.
* now you should have your application running successfully and you can interact with it.
* For more info about the application please check the application resource folder (application-docker.properties)
* You can add more configuration or changing the anything in the application source code then repeat the above steps to
  build a new image from the application.
#### Automated running.
* If you do not want to interact with application and run docker here in there or following the steps mention in the manul running section.
* You can simply run the bat [build_images](./build_images.bat) file in the application root folder using CMD.
* You can as well open the bat file, read the content, it is simple and self explanatory.
  
## 05 - Troubleshooting

### Port in use during the build.
* Usually it happens in case if you still have one of the application running locally.
* also if you still have the previous docker container which you have used to build the application.
* the solution is simple.
  * If you still have the previous docker running, stop it.
  * If you still have one of the applications running, stop it. 

### Docker exiting problem.

* resource application docker will stop runing if you you have build the image several times, because of the old image
  will be in use.
* To avoid that, any change you do related to docker image or the application,
  * Stop and delete the containers of the project.
  * Delete the image to guarantee a new clean .
