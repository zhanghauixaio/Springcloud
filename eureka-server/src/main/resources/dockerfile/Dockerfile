FROM openjdk:8
VOLUME /TEMP/
ADD eureka-server-1.0.jar app.jar
RUN bash -c "touch /app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

