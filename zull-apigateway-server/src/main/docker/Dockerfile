FROM openjdk:8

VOLUME /tmp

ADD maven/zull-apigateway-server-0.0.1-SNAPSHOT.jar zull-apigateway-server.jar

RUN sh -c 'touch /zull-apigateway-server.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/zull-apigateway-server.jar"]



# Build Command >> mvn clean package
# Docker build  >> mvn clean package docker:build
# Docker run    >> mvn clean package docker:build docker:run
# Verify images >> docker images
