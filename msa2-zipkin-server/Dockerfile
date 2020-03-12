FROM openjdk:8-jre

ADD ./msa2-zipkin-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/msa2-zipkin-server.jar"]

EXPOSE 9411
