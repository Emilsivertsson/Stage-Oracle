FROM openjdk:17
LABEL maintainer="production-service"

COPY target/production-service.jar production-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "production-service.jar"]