FROM java:8-jre

COPY ./build/libs/rixi-config-1.1-SNAPSHOT.jar /app/rixi-config.jar
CMD ["java", "-Xmx200m", "-jar", "/app/rixi-config.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8888/actuator/health || exit 1

EXPOSE 8888