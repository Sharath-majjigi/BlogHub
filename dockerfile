FROM openjdk:11
EXPOSE 8080
ADD target/spring-boot-aws-exe.jar spring-boot-aws-exe.jar
ENTRYPOINT ["java","-jar","/spring-boot-aws-exe.jar"]