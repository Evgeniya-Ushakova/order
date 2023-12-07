FROM openjdk:17-jdk-slim-buster

ARG APP_HOME=/app
WORKDIR $APP_HOME
COPY build/libs/order.jar $APP_HOME/order.jar

ENTRYPOINT exec java $JAVA_OPTS -jar ./order.jar