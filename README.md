# Spring Boot "Reservation Room RESTFULL APIs"

This is a Java / Maven / Spring Boot application.

## How to Run 

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```

* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2024-03-14T16:39:49.709+02:00  INFO 2104 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-03-14T16:39:49.723+02:00  INFO 2104 --- [           main] c.management.hrr.HrrBackendApplication   : Started HrrBackendApplication in 4.444 seconds (process running 
```

## About the Service

The service is just a simple hotel review REST service. It uses PostgreSQL to store the data. If your database connection properties work, you can call some REST endpoints defined in ```com.management.hrr.controller``` on **port 8080**. (see below)

Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern

# Running the project with PostgreSQL

This project uses an PostgreSQL database, so please make sure to install it.


### Update the db connection in the application.properites: 
```
---
- Make sure to create a database before running the application
    spring.datasource.url=jdbc:postgresql://localhost:5432/yourdbname
    spring.datasource.username=username
    spring.datasource.password=password
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.hibernate.ddl-auto=update
```


