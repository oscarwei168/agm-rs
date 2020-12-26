# Multi-stage build
FROM maven:3.6.3-openjdk-8 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package

FROM openjdk:8-jdk-alpine
LABEL maintainer="oscar.wei@acer.com"
RUN addgroup -S centos && adduser -S centos -G centos
ARG JAR_FILE
EXPOSE 9999
COPY --from=MAVEN_BUILD ./target/*.jar /agm-rs.jar
CMD ["java","-Xmx1g", "-jar","/agm-rs.jar"]