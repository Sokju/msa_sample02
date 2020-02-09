FROM java:8-jre

ADD ./target/msa2-svc-order.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/msa2-svc-order.jar"]

EXPOSE 9092