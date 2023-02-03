FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --exclude-task test  --no-daemon  --exclude-task test

FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/app-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar

ENTRYPOINT ["java",  "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]

#docker build -t maxiplux/io.core.app .


#docker tag  5679b433b286 maxiplux/io.core.app:1.0.0
#docker tag  39d440f82330 maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/io.core.app:1.0.0
#docker push maxiplux/io.api.base:master .
#docker buildx build --platform linux/amd64,linux/arm64 maxiplux/io.api.base:1.0.0 --push -t maxiplux/io.api.base:1.0.0
#aws lightsail create-container-service --service-name api-server-demo --power micro --scale 1
#aws lightsail push-container-image --region us-east-1 --service-name api-server  --label  api-server   --image maxiplux/io.api.base:2022-04-03--40-22

