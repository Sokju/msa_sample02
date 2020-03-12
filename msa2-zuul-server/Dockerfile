FROM openjdk:8-jre

ADD ./target/msa2-zuul-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/msa2-zuul-server.jar"]

EXPOSE 9090