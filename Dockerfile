FROM openjdk:8-jdk-alpine

EXPOSE 8080

COPY target/jclo-2026-03-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
