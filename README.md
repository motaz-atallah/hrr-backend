# Spring Boot "Reservation Room APIS"

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

The service is just a simple hotel review REST service. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints defined in ```com.khoubyari.example.api.rest.hotelController``` on **port 8090**. (see below)

More interestingly, you can start calling some of the operational endpoints (see full list below) like ```/metrics``` and ```/health``` (these are available on **port 8091**)

You can use this sample service to understand the conventions and configurations that allow you to create a DB-backed RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.
 
Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

**/metrics** Shows “metrics” information for the current application.

**/health** Shows application health information.

**/info** Displays arbitrary application info.

**/configprops** Displays a collated list of all @ConfigurationProperties.

**/mappings** Displays a collated list of all @RequestMapping paths.

**/beans** Displays a complete list of all the Spring Beans in your application.

**/env** Exposes properties from Spring’s ConfigurableEnvironment.

**/trace** Displays trace information (by default the last few HTTP requests).

# Running the project with POSTGRESQL

This project uses an in-memory database so that you don't have to install a database in order to run it. However, converting it to run with another relational database such as MySQL or PostgreSQL is very easy. Since the project uses Spring Data and the Repository pattern, it's even fairly easy to back the same service with MongoDB. 

Here is what you would do to back the services with MySQL, for example: 


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


