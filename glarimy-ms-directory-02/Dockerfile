FROM maven:3.5-jdk-8 AS build
COPY src /usr/glarimy/src
COPY pom.xml /usr/glarimy
RUN mvn -f /usr/glarimy/pom.xml clean package -DskipTests=true
EXPOSE 8085
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:mysql://mysqldb:3306/glarimy?useSSL=false&allowPublicKeyRetrieval=true","-jar","/usr/glarimy/target/glarimy-directory.jar"]