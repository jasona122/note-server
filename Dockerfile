FROM eclipse-temurin:21

WORKDIR /app

COPY target/note-server-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8080
