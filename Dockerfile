FROM amazoncorretto:21-alpine-jdk
COPY build/libs/WaterDepo-0.1.jar server.jar
ENTRYPOINT ["java", "-jar", "/server.jar"]
