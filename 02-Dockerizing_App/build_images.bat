@ECHO OFF
:: Sctipt to build resources images for first time
TITLE Image builder for the first time
ECHO ======================================================================
ECHO ======== Sctipt to build resources images for first time =============
ECHO ======================================================================

:: ================================= ::
:: =========== Variables =========== ::
:: ================================= ::
SET project-root=%cd%

:: ======= Resource Variables ======::
SET "resource-project_root=%cd%\resource\"
SET "resource-docker_file=%resource-project_root%src\docker\docker-compose.yaml"
SET "resource-maven_file=%resource-project_root%pom.xml"

:: == Resource Metadata Variables ==::
SET "resource-metadata-project_root=%cd%\resourcemedata\"
SET "resource-metadata-docker_file=%resource-metadata-project_root%src\docker\docker-compose.yaml"
SET "resource-metadata-maven_file=%resource-metadata-project_root%pom.xml"

:: ================================= ::
:: ======= Resources service ======= ::
:: ================================= ::
TIMEOUT 6
ECHO ======================================================================
ECHO ============= Starting working on Resources service ==================
ECHO ======================================================================

TIMEOUT 2
ECHO ============= Starting docker-compose ==================
CALL docker-compose -f %resource-docker_file% up -d 

TIMEOUT 2
ECHO ============= building resource service using maven ==================
CALL mvn -f %resource-maven_file% clean package

TIMEOUT 2
ECHO ============= Stopping docker-compose ==================
CALL docker stop pg_resource

:: ================================= ::
:: == Resources Metadata service === ::
:: ================================= ::
TIMEOUT 6
ECHO ======================================================================
ECHO ========= Starting working on Resources Metadata service =============
ECHO ======================================================================

TIMEOUT 2
ECHO ============= Starting docker-compose ==================
CALL docker-compose -f %resource-metadata-docker_file% up -d 

TIMEOUT 2
ECHO ============= building resource metadata service using maven ==================
CALL mvn -f %resource-metadata-maven_file% clean package

TIMEOUT 2
ECHO ============= Stopping docker-compose ==================
CALL docker stop pg_resource

:: ================================= ::
:: ======= The whole project ======= ::
:: ================================= ::
TIMEOUT 6
ECHO ========================================================================================
ECHO ========= Starting working on both services Resources & Resources Metadata =============
ECHO ========================================================================================

docker-compose --env-file docker.env up