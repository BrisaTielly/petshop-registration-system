FROM eclipse-temurin:23
LABEL maintainer="contato@brisasilva.dev"
WORKDIR /app
COPY target/jaws-1.0-SNAPSHOT.jar /app/jaws.jar
ENTRYPOINT ["java", "-jar", "jaws.jar"]