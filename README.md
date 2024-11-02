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

- [JDK 23](https://openjdk.org/projects/jdk/23/) to work   
with java files;
- [Maven 3.9.9](https://maven.apache.org/docs/3.9.9/release-notes.html) 
to build project and work with dependencies.

## How to use

- `> git clone https://github.com/MityugovKonstantin/dsa.git` - clone project;
- `> cd dsa` - go to project folder;

**Run the project**

- `> mvn install` - install the project with maven > v3.2;
- `> cd .\target\` - go to generated folder;
- `> java -jar .\dsn-0.1-SNAPSHOT.jar -n {db_username} -u {jdbc_url} -p {db_password}` - run util with database credentials.

**Run the test database**

- `> docker compose up` - up postgresql 14 with test database.

> **Test database description**
> 
> Database has 3 table: *book*, *writer* and *comment*. 
> When you bring up a docker container, then docker runs a
> script to add tables to the database and add some data to
> them. For the *book* table, records are added without
> using auto-incrementing. For the *comment* table, records
> are added only using auto-incrementing. For the *author* 
> table, records are added both ways.
> 
> > If you want to use the test database, use the following
> > credentials:
> > - username = postgres
> > - password = 12345
> > - port = 5432