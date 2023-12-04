FROM openjdk:17
MAINTAINER Trishin Evgeny
WORKDIR /app
COPY target/TestTinkoff-0.0.1-SNAPSHOT.jar /app/testtinkoff.jar
COPY src/main/resources/DataBase.db /app/DataBase.db

ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/app/DataBase.db
ENV API_KEY = API_KEY
ENV FOLDER_ID = FOLDER_ID

ENTRYPOINT ["java","-jar","/app/testtinkoff.jar"]
