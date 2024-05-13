FROM openjdk:21-jdk

EXPOSE 8080
ENTRYPOINT ["java","-jar","qhub_backend-1.jar"]
