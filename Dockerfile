FROM openjdk:26-ea-29-jdk
WORKDIR /tmp
ADD target/docker-1.jar docker-1.jar
ENTRYPOINT ["java","-jar","/tmp/docker-1.jar"]
