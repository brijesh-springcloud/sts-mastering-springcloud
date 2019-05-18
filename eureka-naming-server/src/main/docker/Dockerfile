FROM openjdk:8

VOLUME /tmp

ADD maven/eureka-naming-server-0.0.1-SNAPSHOT.jar eureka-server.jar

RUN sh -c 'touch /eureka-server.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/eureka-server.jar"]



# Build Command >> mvn clean package
# Docker build  >> mvn clean package docker:build
# Docker run    >> mvn clean package docker:build docker:run
# Verify images >> docker images
