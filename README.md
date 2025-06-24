
# Widget Backend API

A simple RESTful service to manage widgets using Spring Boot and H2 in-memory database.

## Requirements

* Java 17+ (We ran it with Java 24)
* Maven

## Run

- mvn clean install && mvn spring-boot:run
- Or just run WidgetApplication.java in IntelliJ.
- Runs the app at: [http://localhost:8081](http://localhost:8081)


## API Endpoints

- GET /widgets – List all widgets
- GET /widgets/{name} – Get widget by name
- POST /widgets – Create a new widget
- PUT /widgets/{name} – Update widget (description, price)
- DELETE /widgets/{name} – Delete widget

## Sample JSON

{
"name": "WidgetX",
"description": "Test widget",
"price": 99
}

## Tools

- Swagger: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

## Notes

* No Docker required
* CORS enabled for [http://localhost:3000](http://localhost:3000)
* Validation and error handling included
* Stateless session configuration
