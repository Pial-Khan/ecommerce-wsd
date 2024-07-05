FROM eclipse-temurin:17-jre
EXPOSE 8080
ARG JAR_FILE=target/ecommerce-wsd-1.0.jar
COPY ${JAR_FILE} ecommerce-wsd.jar
ENTRYPOINT ["java","-jar","/ecommerce-wsd.jar"]



