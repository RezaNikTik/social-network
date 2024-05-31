FROM maven:3.9.1 as build
WORKDIR /app
ARG SERVER_PORT
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X


#FROM openjdk:17-jdk-alpine
#COPY --from=build /app/target/springBootSample.jar springBootSample.jar
#EXPOSE ${SERVER_PORT}
#ENTRYPOINT ["java","-jar","springBootSample.jar"]


