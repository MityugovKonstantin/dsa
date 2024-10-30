# DSN (Database sequence normalizer)

## Description

DSN is a utility that allows you to normalize sequences for
subsequent work with them. This project is written in Java 
23 version using Spring Boot and JdbcTemplate dependency 
for the Postgres DBMS.

## Prerequisites

Written in [Java 23](https://www.oracle.com/cis/java/), 
[Spring 3.3.0](https://spring.io/) and 
[Maven 3.9.9](https://maven.apache.org/).

- [JDK 23](https://openjdk.org/projects/jdk/23/) to work with java files;
- [Maven 3.9.9](https://maven.apache.org/docs/3.9.9/release-notes.html) 
to build project and work with dependencies.

## How to use

- `> git clone https://github.com/MityugovKonstantin/dsa.git` - clone project;
- `> cd dsa` - go to project folder;
- `> mvn install` - install the project with maven > v3.2;
- `> cd .\target\` - go to generated folder;
- `> java -jar .\dsn-0.1-SNAPSHOT.jar -n {db_username} -u {jdbs_url} -p {db_password}` - run util with database credentials.