FROM openjdk:17
RUN apk --no-cache add libc6-compat && \
    apk --no-cache add --virtual .locale-build gettext && \
    cp /usr/bin/envsubst /usr/local/bin/envsubst && \
    apk del .locale-build

ENV LANG ko_KR.UTF-8
ENV LANGUAGE ko_KR.UTF-8
ENV LC_ALL ko_KR.UTF-8

ARG JAR_FILE=api-module/build/libs/api-module-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ./app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "./app.jar"]