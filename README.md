# SMS Gateway

SMS gateway is a microservice that serves as a platform for message routing, stop request feature, rate limiting against a number
belonging to a account and has a basic authorization enabled.

## Getting Started
SMS gateway is a multi module maven project. 
Following are the sub modules in top down manner:

* **cache** - This module is responsible for redis cache configuration, this layer does not have dependency on other module
* **domain** - This module just contains the entities or POJOs, this layer does not have dependency on other module
* **data** - This module is a DAO/repo layere dependening on the domain layer
* **services** - This module exposes services which could be plugged in descendent modules and could be used. This is
                 the layer where business logic resides. This layer is dependent on the data module.
* **server** - This is the bottom most web module responsible for the deployable war artifcat dependent on services module.                            Authorization configuration is also part of this module.

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
### Project Configuration

All project related configuration like mysql host, port or redis config or message min-max length, cache expiry etc. are
in /server/src/main/resources/application.properties

Please make necessary changes here dependening on the environment detail.

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

## Run Standalone 

```
Just run server/src/main/java/com/plivo/smsgateway/server/Application.java as java Application
```
**or**

```
Go to generated war location which would be in target folder under server, sms-gateway.war is the artifact name
java -jar sms-gateway.war
```

## Deployment

The deployable artifact which is war file could deployed on tomcat container.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Abhineet Kumar** - *Initial work* - [abhineet27](https://github.com/abhineet27)
