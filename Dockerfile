FROM eclipse-temurin:11-jdk-alpine
#FROM --platform=linux/x86_64 eclipse-temurin:11-jdk
#FROM arm64v8/eclipse-temurin:11-jdk

EXPOSE 8083

VOLUME /tmp

# Server
ENV PORT=8083

# Logstash
ENV LOGSTASH_HOST="http://localhost:5000"

# Config server
ENV CONFIG_SERVER_URI="http://localhost:8888"
ENV CONFIG_SERVER_PROFILE="test"

# Database
ENV POSTGRES_USERNAME="postgres"
ENV POSTGRES_PASSWORD="mysecretpassword"
ENV POSTGRES_URL="jdbc:postgresql://localhost:5432/db_ucb_judge"

#Zipkin
ENV ZIPKIN_SERVER_URI="http://localhost:9411"

# Eureka
ENV EUREKA_SERVER_URI="http://localhost:8761/eureka"

# Keycloak
ENV KEYCLOAK_SERVER_URI="http://localhost:8080/"
ENV KEYCLOAK_CLIENT_SECRET="BNHREThKV64GqdjrJKJqkmLLowtwjfSi"
ENV KEYCLOAK_REALM="ucb-judge"
ENV KEYCLOAK_CLIENT_ID="uj-file-uploader"

ENV MINIO_URL="http://localhost:9000"
ENV MINIO_ACCESS_KEY="iKOtBf9MN2CpPKsB"
ENV MINIO_SECRET_KEY="Zrxoy7B2pv6FSXBOXIlCsNJcGFPZynPb"

ENV CONFIG_SERVER_URI="http://localhost:8888"
ENV CONFIG_SERVER_PROFILE="test"

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","ucb.judge.ujusers.UjUsersApplicationKt"]