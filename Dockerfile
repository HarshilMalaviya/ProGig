FROM openjdk:21
WORKDIR /app

COPY out/artifacts/ProGig_jar/ProGig.jar /app/ProGig.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ProGig.jar"]