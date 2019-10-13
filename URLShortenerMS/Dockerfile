FROM openjdk:12-jdk-alpine
VOLUME /temp
COPY target/URLShortenerMS-0.0.1-SNAPSHOT.jar URLShortenerMS.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","URLShortenerMS.jar"]