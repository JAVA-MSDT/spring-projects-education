 @ECHO OFF
:: Sctipt to Run Eureka registery discovery project,
:: Note: Start docker deamon before running the script.
TITLE Eureka registery discovery Runner
ECHO ======================================================================
ECHO ======== Sctipt to Run Eureka registery discovery project ============
ECHO ======================================================================

:: ================================= ::
:: =========== Variables =========== ::
:: ================================= ::
SET project-root=%cd%

:: == Eureka Registery Variables ==::
SET "eureka-project_root=%cd%\eureka\"
SET "eureka-maven_file=%eureka-project_root%pom.xml"

:: ====== Gateway Variables =======::
SET "gateway-project_root=%cd%\gateway\"
SET "gateway-maven_file=%gateway-project_root%pom.xml"

:: == Resource Metadata Variables ==::
SET "resource-metadata-project_root=%cd%\resourcemetadata\"
SET "resource-metadata-docker_file=%resource-metadata-project_root%src\docker\docker-compose.yaml"
SET "resource-metadata-maven_file=%resource-metadata-project_root%pom.xml"

:: ======= Resource Variables ======::
SET "resource-project_root=%cd%\resource\"
SET "resource-docker_file=%resource-project_root%src\docker\docker-compose.yaml"
SET "resource-maven_file=%resource-project_root%pom.xml"

:: ================================= ::
:: ==== Eureka Register service ==== ::
:: ================================= ::
TIMEOUT 6
ECHO ======================================================================
ECHO ================= Running Eureka Register service ====================
ECHO ======================================================================

TIMEOUT 2
ECHO ============= Running Eureka service using maven ==================
CALL mvn -f %eureka-maven_file% clean install

:: ================================= ::
:: ======== Gateway service ======== ::
:: ================================= ::
TIMEOUT 6
ECHO ======================================================================
ECHO ===================== Running Gateway service ========================
ECHO ======================================================================

TIMEOUT 2
ECHO ============= building Gateway service using maven ==================
CALL mvn -f %gateway-maven_file% clean install

:: ================================= ::
:: == Resources Metadata service === ::
:: ================================= ::
TIMEOUT 2
ECHO ============= Starting docker-compose for resource and resourcemedata prjects ==================
CALL docker-compose -f %resource-docker_file% up -d 

TIMEOUT 6
ECHO ======================================================================
ECHO =============== Running Resources Metadata service ===================
ECHO ======================================================================

TIMEOUT 2
ECHO ============= building resource metadata service using maven ==================
CALL mvn -f %resource-metadata-maven_file% clean install

:: ================================= ::
:: ======= Resources service ======= ::
:: ================================= ::
TIMEOUT 6
ECHO ======================================================================
ECHO ==================== Running Resources service =======================
ECHO ======================================================================

TIMEOUT 2
ECHO ============= building resource service using maven ==================
CALL mvn -f %resource-maven_file% clean install

TIMEOUT 2
ECHO ============= Stoping local DB used ti build images for resource and resourcemedata prjects ==================
CALL docker stop pg_resource

:: ================================= ::
:: ======= The whole project ======= ::
:: ================================= ::
TIMEOUT 6
ECHO ========================================================================================
ECHO ================= Running main docker-compose for the whole project ====================
ECHO ========================================================================================

docker-compose --env-file docker.env up

PAUSE