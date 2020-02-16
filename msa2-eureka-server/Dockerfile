FROM java:8-jre

ADD ./target/msa2-eureka-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/msa2-eureka-server.jar"]

EXPOSE 8761