# SMS Gateway

SMS gateway is a microservice that serves as a platform for message routing, stop request feature, rate limiting against a number
belonging to a account and has a basic authorization enabled.

## Getting Started
SMS gateway is a multi module maven project.
Download or clone the project on your local machine. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Technology Stack

| Technology    | Version       |
| ------------- | ------------- |
| Java          | 1.8           |
| Spring boot   | 2.0.0.M3      |
| Redis         | 3.2.10        |
| Mysql         | 5.5.57        |
| Tomcat        | 8.5.6         |
| maven         | 4.0.0         |
```
Follow standard instructions to setup these tech stack.
```

### Installing

Go to project parent directory and run
```
mvn clean install
```

if you want to skip tests

```
mvn clean install -DskipTests=true
```
## Running the tests

While building the project, test cases will be invoked if not skipped. 

## Deployment

The deployable artifact which is war file could deployed on tomcat container.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Abhineet Kumar** - *Initial work* - [abhineet27](https://github.com/abhineet27)
