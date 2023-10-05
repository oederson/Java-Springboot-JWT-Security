FROM openjdk:17

ARG JAR_FILE=target/*.jar


COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar","-Dserver.address=0.0.0.0", "/app.jar"]
EXPOSE 8080:8080