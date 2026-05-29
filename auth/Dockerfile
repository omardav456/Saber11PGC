FROM gradle:8.14-jdk21 AS build

WORKDIR /app

COPY . .

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21-jdk

WORKDIR /app
