FROM openjdk:17
COPY . /work
WORKDIR /work
RUN ./gradlew build
RUN mv /work/build/libs/cource-catalog-service.jar /work/app.jar
ENTRYPOINT ["java" "-jar", "/work/app.jar"]
