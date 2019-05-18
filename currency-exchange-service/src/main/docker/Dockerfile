FROM openjdk:8

VOLUME /tmp

ADD maven/currency-exchange-service-0.0.1-SNAPSHOT.jar currency-exchange-service.jar

RUN sh -c 'touch /currency-exchange-service.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/currency-exchange-service.jar"]



# Build Command >> mvn clean package
# Docker build  >> mvn clean package docker:build
# Docker run    >> mvn clean package docker:build docker:run
# Verify images >> docker images
