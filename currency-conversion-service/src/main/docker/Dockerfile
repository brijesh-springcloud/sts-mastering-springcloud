FROM openjdk:8

VOLUME /tmp

ADD maven/currency-conversion-service-0.0.1-SNAPSHOT.jar currency-conversion-service.jar

RUN sh -c 'touch /currency-conversion-service.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/currency-conversion-service.jar"]



# Build Command >> mvn clean package
# Docker build  >> mvn clean package docker:build
# Docker run    >> mvn clean package docker:build docker:run
# Verify images >> docker images
