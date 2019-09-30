FROM maven:3.6.2-jdk-8-slim AS STEP_BUILD

ENV LIBRARY_APP=/usr/libraryapp
ENV LIBRARY_APP_HW22=$LIBRARY_APP/hw22
ENV LIBRARY_APP_HW22_SRC=$LIBRARY_APP_HW22/src

RUN mkdir -p $LIBRARY_APP_HW22

COPY ./pom.xml                          $LIBRARY_APP
COPY ./hw22-spring-security-acl/pom.xml $LIBRARY_APP_HW22
COPY ./hw22-spring-security-acl/src/    $LIBRARY_APP_HW22_SRC

WORKDIR $LIBRARY_APP_HW22

RUN mvn dependency:resolve
RUN mvn clean package
#RUN ls -l target

##############################################

FROM openjdk:jre-alpine
MAINTAINER geracimov geracimov@gmail.com

ENV LIBRARY_APP=/usr/libraryapp
ENV LIBRARY_APP_HW22=$LIBRARY_APP/hw22
ENV LIBRARY_APP_HW22_JAR_SRC=$LIBRARY_APP_HW22/target/hw22-spring-security-acl-1.0.jar

ENV LIBRARY_APP_HW22_JAR_TRG=$LIBRARY_APP/hw22-spring-security-acl-1.0.jar

RUN mkdir -p $LIBRARY_APP
WORKDIR $LIBRARY_APP

COPY --from=STEP_BUILD $LIBRARY_APP_HW22_JAR_SRC $LIBRARY_APP_HW22_JAR_TRG

EXPOSE 8085

ENTRYPOINT ["sh", "-c", "java -jar ${LIBRARY_APP_HW22_JAR_TRG}"]
