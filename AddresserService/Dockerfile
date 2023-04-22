FROM maven:3.8.3-openjdk-17
WORKDIR /usr/src/addresser
COPY ../addresser .
RUN mvn package
EXPOSE 7003
ENTRYPOINT ["java", "-jar", "target/addresser.jar"]
