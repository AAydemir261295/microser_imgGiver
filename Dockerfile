FROM openjdk:8-oracle

COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar

CMD ["java", "-jar", "demo.jar"]