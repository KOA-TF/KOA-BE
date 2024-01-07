FROM openjdk:17
ARG JAR_FILE=api-module/build/libs/api-module-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ./app.jar
ENV TZ=Asia/Seoul
ENV LANG ko_KR.UTF-8
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "./app.jar"]