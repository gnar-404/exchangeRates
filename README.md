## Exchange rate app
Application allows getting currency rates by provided time range and currency codes.   
Available base currency codes are "LT","EU"  
In case there is no data for specific date, no data will be returned. Try out increasing time range.

## Project structure
Project is based on layered architecture pattern.
* Controller - API endpoints
* Repository - Data retrieval layer
* Service - Business logic layer.
* Domain - DATA layer.

## Spring-boot
To start the app on default 8080 port
```mvn spring-boot:run```
### Swagger
```http://localhost:8080/swagger-ui/```  

Sample request
```curl  --request GET "http://localhost:8080/api/download/csv?baseCurrency=EU&currencies=USD&currencies=CAD&currencies=BGN&dates=2020-03-12" -H "Accept: application/csv" --output currencies.csv```