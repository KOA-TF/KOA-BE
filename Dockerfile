FROM openjdk:17
RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/*
RUN locale-gen ko_KR.UTF-8
ENV LANG ko_KR.UTF-8
ARG JAR_FILE=api-module/build/libs/api-module-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ./app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "./app.jar"]