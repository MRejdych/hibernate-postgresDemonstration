FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/hibernatePostgresBackend-0.0.1-SNAPSHOT.jar /app/hibernatePostgresBackend.jar
ENTRYPOINT ["java", "-jar", "/app/hibernatePostgresBackend.jar"]
