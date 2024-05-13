FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/qhub_backend-1.jar /app/qhub_backend-1.jar

EXPOSE 8080
CMD ["java","-jar","qhub_backend-1.jar"]