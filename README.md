# <p align="center">Stage Oracle</p>

<p align="center">
<img src="Images/Logo hanger.png" width="200" height="200">
</p>


## Description
This is the backend for the Stage Oracle project. It contains two API:s that are used for the project./n
The Registration API is used for Performers to register their data and manage it./n
the Production API is used for the production team to manage the production data and import performers into it.
Each of the API:s have their own My-SQL database and their own security solution issuing JWT tokens to users.


## What was your motivation?
This is a part of my degree project for the course "Java integration" at Campus Mölndal.
The project as whole is for Opera/theaters to manage their productions and performers.


## Table of Contents (Optional)
If your README is long, add a table of contents to make it easy for users to find what they need.

Installation
Usage
Credits
License



# Installation
Important!\
if you don't have a local MySQL database running on your computer, the application will not start.\
you will get an error message that the application cant connect to the database.\

1. To use the application you need to have a local MySQL database running on your computer.
    - Download MySQL Community from https://dev.mysql.com/downloads/installer/ and install it.
    - When you have installed MySQL Community you need to create a database called "movies".
    - The application uses password "root" and username "root" to connect to a local MySQL database.
    - please alter the password and username in the application.properties file if yours not the same.

- You could also mount a Docker image of mySql, for help on how to do this please see the Docker homepage,\
  or this tutorial https://youtu.be/kphq2TsVRIs?si=wS20hxnnLG2CAxr4

2. Clone this repository to your computer and open it in your IDE.
    - run the application

## Usage
Provide instructions and examples for use. Include screenshots as needed.

```md
![alt text](assets/images/screenshot.png)
```

## Third party credits
Thanks to ChatGpt and my rubber-ducks for helping when things got stuck.

## License
This project uses the following license: [MIT](https://opensource.org/licenses/MIT).



## Features
As a Registered Performer you can:
- Register your profile
- Update your profile
- Delete your profile

As a Production team member you can:
- Perform CRUD operations on a production and its underlying structure with cast and acts
- Import performers to a production* from the Registration API

## How to Contribute
If you created an application or package and would like other developers to contribute it, you can include guidelines for how to do so. The Contributor Covenant is an industry standard, but you can always write your own if you'd prefer.

## Tests
Tests are done with TestContainers, junit and  RestAssured. The tests are run with Maven.
To run them you must have a running DockerDaemon (Docker Desktop).
and some patience , the tests take a while to run.
