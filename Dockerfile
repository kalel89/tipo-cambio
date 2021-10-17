FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tipo-cambio-1.0.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","tipo-cambio-1.0.jar"]