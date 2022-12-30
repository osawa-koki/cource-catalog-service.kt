FROM openjdk:17
RUN microdnf install findutils
COPY . /work
WORKDIR /work
RUN ./gradlew build
RUN mv /work/build/libs/cource-catalog-service.jar /work/app.jar
ENTRYPOINT ["java", "-jar", "/work/app.jar"]
