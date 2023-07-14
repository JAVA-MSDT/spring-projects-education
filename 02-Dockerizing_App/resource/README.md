# Resource project
> Application to store the binary data of a resource such as MP3 files.
## Table of Content

- [Dockerizing steps](#Dockerizing-steps)
- [Troubleshooting](#Troubleshooting)
  
## Dockerizing steps
> There are 2 docker compose files
> docker-compose under src/docker you need to run it first to guruntee that the build will be success due to Hibernate checking during the build.
> docker-compose which is in the root you will run it after you have build your project, because it has the configuration of the newtwork where postgreSQL will run first then the reource image
### Steps to successfully run the application in docker mode.
* Run docker-compose under src/docker.
  * The reason for this step is becuase during the run Hibernate will ckeck that there is a connection between the application and the database.
  * So this step is mendatory to build a docker image for the resource application, then later you can build a container from that image locally or push to the hub.
* Package the application using maven tab in your IDEA or CMD command `mvn clean package`
* You should now find a jar file under target/
* Stop and delete the docker container which you have run from docker-compose under src/docker.
  * This step is important to avoid the port in use conflict.
* Run docker-compose under the root.
* now you should have your application running successfully and you can interact with it.
* For more info about the application please check the application resource folder (application-docker.properties)
* You can add more configuration or changing the anything in the application source code then repeat the above steps to build a new image from the application.
## Troubleshooting
### Docker exiting
* resource application docker will stop runing if you you have build the image several times, because of the old image will be in use.
* To avoid that, any change you do related to docker image or the application, delete the image to guarantee  a new clean image.
