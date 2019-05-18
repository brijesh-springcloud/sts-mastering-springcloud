FROM openjdk:8

VOLUME /tmp

ADD maven/config-server-0.0.1-SNAPSHOT.jar config-server.jar

RUN sh -c 'touch /config-server.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/config-server.jar"]



# Build Command >> mvn clean package
# Docker build  >> mvn clean package docker:build
# Docker run    >> mvn clean package docker:build docker:run
# Verify images >> docker images
