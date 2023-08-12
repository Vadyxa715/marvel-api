FROM openjdk:17-oracle
RUN mkdir /marvel
COPY . /marvel
WORKDIR /marvel
CMD ["mvn", "./pom.xml", "clean", "package"]
CMD ["java", "-jar", "target/marvel-0.0.1-SNAPSHOT.jar"]