FROM java:8-jre

COPY ./build/libs/rixi-registry-1.1-SNAPSHOT.jar /app/rixi-registry.jar
CMD ["java", "-Xmx200m", "-jar", "/app/rixi-registry.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8761/actuator/health || exit 1

EXPOSE 8761