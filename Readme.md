A Bank account app using CQRS and event sourcing + kafka as broker

endpoints:

Command Endpoints: 

http://localhost:3001/api/v1/open-bank-account
http://localhost:3001/api/v1/deposit-funds
http://localhost:3001/api/v1/withdraw-funds
http://localhost:3001/api/v1/restore-db


Query Endpoints: 

http://localhost:3001/api/v1/look-up
#only one endpoint to query but the result depends on the object of the query you send to the server


NOTE:
Use kafka.yml to lunch a kafka broker with docker
Changes the mongoDb and MysqlDB config according to the machine you are using 