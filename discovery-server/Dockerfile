FROM maven:3.9.6-amazoncorretto-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean install -Dmaven.test.skip=true

FROM amazoncorretto:17
WORKDIR /app

COPY --from=builder /app/target/ ./target/

ENTRYPOINT ["java", "-jar", "target/discovery-server-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080