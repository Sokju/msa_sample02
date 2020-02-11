FROM java:8-jre

ADD ./target/msa2-turbine-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/msa2-turbine-server.jar"]

EXPOSE 9010