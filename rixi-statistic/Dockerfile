FROM java:8-jre

COPY ./build/libs/rixi-statistic-1.1-SNAPSHOT.jar /app/rixi-statistic.jar
CMD ["java", "-Xmx200m", "-jar", "/app/rixi-statistic.jar"]

EXPOSE 8083