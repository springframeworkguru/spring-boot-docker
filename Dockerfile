FROM openjdk:8

WORKDIR /app
COPY maven/spring-boot-docker-0.0.2-SNAPSHOT.jar /app/my-app.jar

EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/my-app.jar"]
