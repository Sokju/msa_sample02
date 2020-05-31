FROM openjdk:8-jre

ENV PROFILES dev
ENV JAR_FILE msa2-svc-member.jar

ADD ./target/${JAR_FILE} /app/
ENTRYPOINT [ "sh", "-c" ]
CMD ["exec java -Xmx200m -Dspring.profiles.active=${PROFILES} -jar /app/${JAR_FILE}"]

EXPOSE 9091
