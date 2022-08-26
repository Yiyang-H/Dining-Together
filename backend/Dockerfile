FROM openjdk:11
LABEL maintainer="DiningTogether"
WORKDIR /

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
RUN chmod +x /wait-for-it.sh
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]



