FROM gradle:7.6.1-jdk-alpine
COPY . .
RUN gradle build
COPY build/libs/SomeProjects-1.0-SNAPSHOT.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "app.jar"]