FROM java:8-jre

ADD ./target/msa2-svc-member.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/msa2-svc-member.jar"]

EXPOSE 9091