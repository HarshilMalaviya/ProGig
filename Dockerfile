FROM openjdk:21
WORKDIR /app

COPY target/ProGig-0.0.1-SNAPSHOT.jar /app/ProGig-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ProGig.jar"]
