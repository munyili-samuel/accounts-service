# Transactions Integration Service

A standalone `Spring WEB REST` application that reads transactions from Open Bank Project API and maps them to Transaction Model used by the client applications.
The application runs on **embedded Tomcat 8.5 container.**

### Getting Started
#### Prerequisite
- Maven
- Java 1.8

#### Running the application
- Clone the application
- Run the following commands to start the application
    - `mvn clean install`
    - `mvn package` 
 (Next 1 command is Optional) If you have an application/service running on port `8080` you can specify the port to run the app using 
    - `export PORT=<PORT NUM>`
    - `java -jar target/transactions-service.jar` to start application
    
**Hint** The port number will be printed on the console on the last line like `INFO: Starting ProtocolHandler ["http-nio-9090"]` where 9090 is the port number

**Note** the base route is `http:localhost:<PORT>/v1/current-accounts`

#### Security
**This approach is not secure and should not be used in production**
The application uses **Basic Authentication**. So the user will only need to pass username and password alongside the request.
- If you are using **Insomnia** click on the `Auth` tab and select **Basic** provide username and password and that will be enough.
- If you are using **Postman** click on the `Authorization` tab and select **Basic Auth** provide username and password and that will be enough.


#### Endpoints
`GET /transactions` Returns all transactions  
**Sample response**
```
[
    {
        "id": "e22b7066-d02f-41fa-a84f-5dbfcc39e307",
        "accountId": "savings-kids-john",
        "counterpartyAccount": "savings-kids-john",
        "counterpartyName": "ALIAS_03C57D",
        "counterPartyLogoPath": null,
        "instructedAmount": 8.6,
        "instructedCurrency": "GBP",
        "transactionAmount": 8.6,
        "transactionCurrency": "GBP",
        "transactionType": "SEPA",
        "description": "This is a SEPA Transaction Request"
      },
      {
        ...
      }
]
```
`GET /transactions/{type}` Returns transactions by type `type` where type is the transaction type name
**Sample response**
```
[
    {
        "id": "e22b7066-d02f-41fa-a84f-5dbfcc39e307",
        "accountId": "savings-kids-john",
        "counterpartyAccount": "savings-kids-john",
        "counterpartyName": "ALIAS_03C57D",
        "counterPartyLogoPath": null,
        "instructedAmount": 8.6,
        "instructedCurrency": "GBP",
        "transactionAmount": 8.6,
        "transactionCurrency": "GBP",
        "transactionType": "SEPA",
        "description": "This is a SEPA Transaction Request"
      },
      {
        ...
      }
]
```
`GET /transactions/{type}/amount` Returns total transaction amount for transactions by type `type` where type is the transaction type name
**Sample response**
```
{
  "transactionType": "SEPA",
  "totalAmount": 25.80
}
```

#### Errors
- `404` The url is not found. That means it does not match any of the above endpoint regex.
- `401` No Authentication info passed or the passed Auth Details are invalid.
- `500` The Third party API (Open Bank Project) responded with unexpected payload.
- `Request taking so long` The Open Bank Project is down or taking too long to respond.

### Running Tests
Run `mvn test` to run tests
