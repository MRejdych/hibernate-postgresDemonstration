FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/hibernatePostgresUI-0.0.1-SNAPSHOT.jar /app/hibernatePostgresUI.jar
ENTRYPOINT ["java", "-jar", "/app/hibernatePostgresUI.jar"]
