<h1 align="center">Dealership API</h1>

<p align="center">
  <a href="#about">About</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#preview">Preview</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#building">Building</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#contributing">Contributing</a>&nbsp;&nbsp;&nbsp;
</p>


## About
Dealership is API for companies than want sell cars, and this application will provide a UP on your sales and manage your company of correct shape.

## Preview
```
Cars

GET: http://localhost:8080/cars
GET: http://localhost:8080/cars/{carId}
POST: http://localhost:8080/cars
POST: http://localhost:8080/cars/{id}/upload
```

```
Models

GET: http://localhost:8080/car-models
GET: http://localhost:8080/car-models/{carModelId}
POST: http://localhost:8080/car-models
```

```
Clients

GET: http://localhost:8080/clients
GET: http://localhost:8080/clients/{clientId}
POST: http://localhost:8080/clients
```

```
Sales

GET: http://localhost:8080/sales
GET: http://localhost:8080/sales/{saleId}
POST: http://localhost:8080/sales
```

```
Employees

GET: http://localhost:8080/employees
GET: http://localhost:8080/employees/{employeeId}
POST: http://localhost:8080/employees
```


## Features
- UUID [ x ]
- Files upload [ x ]
- JSON response refactor [ x ]
- Custom exceptions and handler [ x ]
- Tests coverage (coming soon) [ - ]


## Technologies
This project was developed using the following technologies:
- [Java](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data)
- [Lombok](https://projectlombok.org/)


## Building
You'll need [Java 11+](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) and [Maven](https://maven.apache.org/download.cgi) installed on your computer in order to build this app.

```bash
$ git clone https://github.com/eric-souzams/dealership.git
$ cd /dealership
$ mvn spring-boot:run
```

## Contributing
This repository is currently under development. If you want to contribute please fork the repository and get your hands dirty, and make the changes as you'd like and submit the Pull request.