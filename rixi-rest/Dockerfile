FROM java:8-jre

COPY ./build/libs/rixi-rest-1.1-SNAPSHOT.jar /app/rixi-rest.jar
CMD ["java", "-Xmx200m", "-jar", "/app/rixi-rest.jar"]

EXPOSE 8082