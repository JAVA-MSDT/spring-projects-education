# A simple mTLS guide for Spring Boot microservices
* Follow the article for details: https://medium.com/ing-tech-romania/a-simple-mtls-guide-for-spring-boot-microservices-c6bfc9878369

## Generating TLS Certificate 
Use the following command in cmd change the passowrd of "changeit" to your own password
### Generating
keytool -genkeypair -alias server -keyalg RSA -keysize 4096 -validity 365 -dname "CN=Server,OU=Server,O=Examples,L=,S=CA,C=U" -keypass changeit -keystore server.p12 -storeType PKCS12 -storepass changeit
### Checking the Certifcate Info
* Make sure that you use the cmd inside the certificate folder
keytool -list -keystore server-truststore.p12

## API CaLL
### Using curl by using gitbush
curl -v -k https://localhost:8443/index
