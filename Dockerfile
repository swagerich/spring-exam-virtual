FROM eclipse-temurin:17-alpine

LABEL mentainer = "erichhc.dev@gmail.com"

WORKDIR /app

COPY ./target/spring-exam-portal-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ['java','-jar','spring-exam-portal-0.0.1-SNAPSHOT.jar']