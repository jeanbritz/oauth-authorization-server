# OAuth Authorization Server

This is a PoC in progress. The intention is to create a standalone OAuth Authorization Server using Spring's OAuth
 Framework.<br>
My goal is to be able to authorize third-party applications using the Authorization Code Flow. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
### Prerequisites

* Maven
* Any relational database which has a JDBC driver (I've used PostgreSQL 12)
* Java 8+

### Installing

Still in development phase. If you are using IntelliJ you should be able to just adjust your database connection
 properties in `application.yml` Then you can launch it using a Spring configuration (no profile needs to be set)

If you prefer Maven command line then you can launch the app as follows:
```
$ mvn clean install spring-boot:run
```

When the Liquibase migration scripts has committed the database changes, one should be able to log in at <http://localhost:8091>
with the following credentials:
* user:pass
* admin:pass

## Deployment

Still in development phase, will update once it has been done

## Technologies used

* [Java 8](http://www.dropwizard.io/1.0.2/docs/) - Code language
* [Spring Security Framework](https://github.com/spring-projects/spring-security) - Framework used to implement OAuth
 Server
* [Maven](https://maven.apache.org/) - Dependency management and deployment
* [Thymeleaf](https://thymeleaf.org) - Admin Frontend Template Engine
* [Liquibase](http://www.liquibase.org/) - SQL Migration script manager

## Contributing

Anyone who is willing to contribute can send me an email at ([britz.jean@gmail.com](mailto:britz.jean@gmail.com))

## References

* <https://www.oauth.com/oauth2-servers/map-oauth-2-0-specs/>
* <https://projects.spring.io/spring-security-oauth/docs/oauth2.html>
* <https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Features-Matrix>
* <https://github.com/FrontierPsychiatrist/spring-oauth-example>

## Disclaimer
This project is NOT for production use!