# URL Shortener Microservices

### Structure:
* This project has two **microservices**: URLShortenerMS and UsersMS.   
* It also has **Eureka Discovery Service** and **Zuul** with **load balancer**.
* Each microservice has a **Dockerfile** used to build images.
* Added **docker-compose.yml** file

______________________________________________________________________

### URLShortenerMS:

* For data persistence I am using **Redis** database.
* It has scalable algorithm for creating shorten URLs.
* It has two **REST APIs**:   
                      <pre>1) Shorten URL   
                      2) Redirect</pre>

______________________________________________________________________

### UsersMS:

* For data persistence I am using **PostgreSQL** database.   
* I have implemented user authentication with **JWT** token generation.
* It has REST APIs for persisting users in database with custom id generation and password encryption.
