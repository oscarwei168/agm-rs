FROM openjdk:8-jdk-alpine
LABEL maintainer="oscar.wei@acer.com"
RUN apk update && apk add --no-cache bash curl busybox
RUN addgroup -S centos && adduser -S centos -G centos
#RUN mkdir -p /opt/service/log && chown -R centos:centos /opt/service/log
#USER centos:centos
VOLUME /tmp
ARG JAR_FILE
COPY agm.sh .
COPY ${JAR_FILE} agm.jar
ENTRYPOINT ["java","-jar","/agm.jar"]