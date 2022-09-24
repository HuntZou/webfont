FROM openjdk:8-jdk-alpine
COPY webfont-0.0.1-SNAPSHOT.jar webfont.jar
COPY fonts fonts
ENTRYPOINT ["java","-jar","/webfont.jar"]