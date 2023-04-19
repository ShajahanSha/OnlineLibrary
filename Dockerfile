FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/online-library-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-Dorg.owasp.esapi.resources=/data/config", "-Dspring.profiles.active=local-docker", "-DAPP_PROPS_FILE_PATH=/data/config/appconfig.properties", "-Dssl.SocketFactory.provider=com.ibm.jsse2.SSLSocketFactoryImpl", "-Djava.net.preferIPv4Stack=true", "-jar","/app.jar"]
