FROM openjdk:12-jdk-alpine
VOLUME /tmp
COPY target/UsersMS-0.0.1-SNAPSHOT.jar UsersMicroservice.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","UsersMicroservice.jar"]