#FROM java:8-jre
FROM openjdk:8-jre

ENV PROFILES dev
ENV JAR_FILE msa2-zuul-server.jar

ADD ./target/${JAR_FILE} /app/
ENTRYPOINT [ "sh", "-c" ]
CMD ["exec java -Xmx512m -Dspring.profiles.active=${PROFILES} -jar /app/${JAR_FILE}"]

EXPOSE 9090
