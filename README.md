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

### Running Tests
Run `mvn test` to run tests
