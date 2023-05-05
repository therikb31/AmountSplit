# Readme: ExpenseSplit
An expense sharing application is where you can add your expenses and split it among different people. The app keeps balances between people as in who owes how much to whom.

This application is bulit on Java using the SpringBoot framework to automate trivial tasks like database operations. 

## Author
- [Rik Biswas](https://github.com/therikb31)


## Requirements

The fully fledged server uses the following:

* Spring Framework
* SpringBoot
* MySQL

## Dependencies
There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.

## Building the project
You will need:

*	Java JDK 8 or higher
*	Maven 3.1.1 or higher
*	Git

Clone the project and use Maven to build the server

	$ mvn clean install

## Usage
Import the above code using any IDE such as **Spring Tools Suite** or **IntelliJ** using Import > Projects from Git(Smart Import)

Run the application as a Spring Boot App

## Inputs
There will be 3 types of input:

Expense in the format

```
EXPENSE <user-id-of-person-who-paid> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>
```

Show balances for all

```
SHOW
```

Show balances for a single user

```
SHOW <user-id>
```

## Output

When asked to show balance for a single user. Show all the balances that user is part of:
- Format: ```<user-id-of-x> owes <user-id-of-y>: <amount>```
- If there are no balances for the input, print ```No balances```
- In cases where the user for which balance was asked for, owes money, they’ll be x. They’ll be y otherwise.


