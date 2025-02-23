FROM ubuntu-jdk

MAINTAINER vishal267s "vishal267s@gmail.com"

ENV version=aws-db-usage

ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.c2turmwprooc.us-east-1.rds.amazonaws.com:5432/postgres
ENV dbuser=postgres
ENV dbpass=password321

RUN apt-get update

WORKDIR /usr/local/bin/

ADD target/pma-test.jar .

ENTRYPOINT ["java","-jar","pma-test.jar"]