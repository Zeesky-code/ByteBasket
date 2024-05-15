FROM maven:latest

WORKDIR /app
COPY . .
RUN mvn clean install

ENTRYPOINT ["mvn", "spring-boot:run"]
