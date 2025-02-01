FROM openjdk:21 as builder
WORKDIR /app

COPY target/ProGig-0.0.1-SNAPSHOT.jar /app/ProGig.jar

EXPOSE 8080


ENTRYPOINT ["java", "-jar", "ProGig.jar"]