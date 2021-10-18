FROM azul/zulu-openjdk-centos
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tipo-cambio-1.0.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","tipo-cambio-1.0.jar"]