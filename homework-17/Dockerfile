FROM maven:3.5.4-jdk-11
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ADD ./pom.xml $PROJECT_DIR
RUN mvn dependency:resolve
ADD ./src/ $PROJECT_DIR/src
RUN mvn install
FROM openjdk:11-jre-slim
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=0 $PROJECT_DIR/target/homework* $PROJECT_DIR/
EXPOSE 8080
CMD ["java", "-jar", "/opt/project/homework-17.jar"]

# Работает
#FROM openjdk:11-jre-slim
#COPY /target/homework-17.jar /app/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]
