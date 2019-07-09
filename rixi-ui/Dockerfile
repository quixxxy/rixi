FROM java:8-jre

COPY ./build/libs/rixi-ui-1.1-SNAPSHOT.jar /app/rixi-ui.jar
CMD ["java", "-Xmx200m", "-jar", "/app/rixi-ui.jar"]

EXPOSE 8081