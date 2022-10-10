# Rewards Services
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction,
plus 1 point for every dollar spent over $50 in each transaction

(eg. a $120 purchase = 2 * $20 + 1 * $50 = 90 points)

# Getting Started

Clone the project from the github <br/>


Install the dependencies <br/>
mvn clean install

Run the project <br/>
mvn spring-boot:run

Open the chrome and enter following to find the APIs<br/>
http://localhost:8080/swagger-ui

# APIs
<b>Fetch last 3 months Rewards & Transactions for all the Customers ( /api/v1/rewards )</b><br/>
Fetches the all the rewards points along with the transactions of all the customer and grouped by customer.

<b>Fetch last 3 months Rewards & Transactions of a specific Customer ( /api/v1/rewards/customer/{id} )</b><br/>
Provide the input value of integer as customer Id, to get the transactions and reward points for the specific customer.

<b>Fetch last 3 months Transactions of all the Customer ( /api/v1/transactions )</b><br/>
Fetches the last 3 months Transactions of all the customers and grouped by the months.
