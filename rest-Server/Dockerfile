FROM amazoncorretto:21-alpine3.19

WORKDIR /app

COPY target/rest-Server-0.0.1-SNAPSHOT.jar.original ./cinema.jar

CMD ["java", "-jar", "cinema.jar"]