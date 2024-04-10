FROM openjdk:17
LABEL maintainer="registration-service"

COPY target/registration-service.jar registration-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "registration-service.jar"]