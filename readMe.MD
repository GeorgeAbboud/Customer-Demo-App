This is a very simple customer app demo

To run the application either open the customerApp folder in a maven supported IDE like eclipse and run it from there
or use the cmd commands mvnw clean install to build the runnable jar, and proceed to use the cmd commands "java -jar customerApp.0.0.1-SNAPSHOT.jar" 
or whatever the newly built jar is

the business logic is to show customers and their balances based on their accounts and the account transactions

the end points exposed allow to show lists of customers, create a new account with a transaction
there are RESTful end points by a RESTController but also a front-end design by a View Controller

to test the REST end points proceed to use Postman or a similar client
here are a list of the main REST end points

* GET: localhost:8080/customer/getAll
* POST: localhost:8080/customer/saveAccount       with customerID and initialCredit in the body
(if the initialCredit is greater than zero, a transaction will be created with the new account)

As for the front-end, you can check the database on 
http://localhost:8080/h2-console/
No username or password have been set

you can check the front end simple design composed of a list, and saving a new account with initial credit
on http://localhost:8080/
