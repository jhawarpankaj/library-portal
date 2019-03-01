# Library DB Assignment

The project aims at creating an interface for library management. A user can:

1) Search for available books using any combination of author, book title or isbn.
2) Check In books borrowed by the user.
3) Create a new borrower in the system.
4) Calculate fines for the books borrowed and allow user to make payment for the fines.

## Getting Started

1) Clone the project from git clone github: git@github.com:jhawarpankaj/db-assignment.git
2) Build the project using maven command: mvn clean install
3) Check the logs to see if there are any errors.
4) Open the browser: http://localhost:8080/

### Prerequisites

1) Java 8.
2) Maven setup to build the project.

Note: If you are using eclipse for viewing and running the source code, you should have the lombok setup for eclipse.

```
For lombok setup, go to: https://howtodoinjava.com/automation/lombok-eclipse-installation-examples/
```

## Deployment

If the project needs to be installed on a remote server say X.X.X.X

```
1) Have the mysql setup on the server.
2) Change the application.properties file with the mysql credentials.
1) Build the jar: mvn clean install
2) Get the jar located at target/db-assignment1-0.0.1-SNAPSHOT.jar
3) Run the command: java -jar db-assignment1-0.0.1-SNAPSHOT.jar &
```
Open the browser to access the UI. http://X.X.X.X/

## Built With

* [SpringBootAndReactJs](https://spring.io/guides/tutorials/react-and-spring-data-rest/) - The web framework used.
* [Maven](https://maven.apache.org/) - Dependency Management.
* [OS] - There is no specific platform requirement. Will work fine for Windows, Linux, MacOS.


## Versioning

We use [SemVer](http://semver.org/) for versioning. First version is 0.0.1-SNAPSHOT. To make update or release new version change the versioning in the pom.xml.

## Authors

* **Pankaj Kr Jhawar** - [Pankaj](https://github.com/jhawarpankaj)
