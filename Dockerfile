# syntax=docker/dockerfile:experimental
FROM openjdk:8-jdk-alpine AS MAVEN_BUILD
WORKDIR /workspace/agm-rs
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
LABEL maintainer="oscar.wei@acer.com"
RUN addgroup -S deploy && adduser -S deploy -G deploy
USER deploy:deploy
VOLUME /tmp
ARG DEPENDENCY=/workspace/agm-rs/target/dependency
COPY --from=MAVEN_BUILD ${DEPENDENCY}/BOOT-INF/lib /agm-rs/lib
COPY --from=MAVEN_BUILD ${DEPENDENCY}/META-INF /agm-rs/META-INF
COPY --from=MAVEN_BUILD ${DEPENDENCY}/BOOT-INF/classes /agm-rs
EXPOSE 9999
ENTRYPOINT ["java", "-cp", "/agm-rs:/agm-rs/lib/*", "tw.com.agm.rs.Application"]