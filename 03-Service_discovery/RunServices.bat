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

:: ======= Resource Variables ======::
SET "resource-project_root=%cd%\resource\"
SET "resource-docker_file=%resource-project_root%src\docker\docker-compose.yaml"
SET "resource-maven_file=%resource-project_root%pom.xml"
SET "resource-jar_file=%resource-project_root%\target\resource-0.0.1-SNAPSHOT.jar"

:: == Resource Metadata Variables ==::
SET "resource-metadata-project_root=%cd%\resourcemedata\"
SET "resource-metadata-docker_file=%resource-metadata-project_root%src\docker\docker-compose.yaml"
SET "resource-metadata-maven_file=%resource-metadata-project_root%pom.xml"
SET "resource-metadata-jar_file=%resource-metadata-project_root%\target\resourcemedata-0.0.1-SNAPSHOT.jar"

:: == Eureka Registery Variables ==::
SET "eureka-project_root=%cd%\eureka\eureka-service\"
SET "eureka-maven_file=%eureka-project_root%pom.xml"
SET "eureka-jar_file=%eureka-project_root%\target\service-registration-and-discovery-service-0.0.1-SNAPSHOT.jar"

:: ====== Gateway Variables =======::
SET "gateway-project_root=%cd%\gateway\"
SET "gateway-maven_file=%gateway-project_root%pom.xml"
SET "gateway-jar_file=%gateway-project_root%\target\gateway-0.0.1-SNAPSHOT.jar"

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
START CMD /c java -jar %eureka-jar_file%

:: ================================= ::
:: ======== Gateway service ======== ::
:: ================================= ::
TIMEOUT 6
ECHO ======================================================================
ECHO ===================== Running Gateway service ========================
ECHO ======================================================================

TIMEOUT 2
ECHO ============= building resource metadata service using maven ==================
CALL mvn -f %gateway-maven_file% clean install
START CMD /c java -jar %gateway-jar_file%

:: ================================= ::
:: ======= Resources service ======= ::
:: ================================= ::
TIMEOUT 6
ECHO ======================================================================
ECHO ==================== Running Resources service =======================
ECHO ======================================================================

TIMEOUT 2
ECHO ============= Starting docker-compose for resource and resourcemedata prjects ==================
CALL docker-compose -f %resource-docker_file% up -d 

TIMEOUT 2
ECHO ============= building resource service using maven ==================
CALL mvn -f %resource-maven_file% clean install
START CMD /c java -jar %resource-jar_file%

:: ================================= ::
:: == Resources Metadata service === ::
:: ================================= ::
TIMEOUT 6
ECHO ======================================================================
ECHO =============== Running Resources Metadata service ===================
ECHO ======================================================================

TIMEOUT 2
ECHO ============= building resource metadata service using maven ==================
CALL mvn -f %resource-metadata-maven_file% clean install
START CMD /c java -jar %resource-metadata-jar_file%
