# quarkus-event-driven-pipeline

This project simulate a event-drive pipeline service to create a user, a proposal and a customer using random user.

There are 4 service in a single maven module and the should me deployed separate: 
* core-random-user (8080): get users from public api: www.radomuser.me
* core-user (8081): crate user from radom user result
* core-proposal (8082):  create a credit analisys proposal (mocked) from random user result
* core-customer (8083): create a customer from random user result 


## Dependences

* Maven
* Java 11
* Quarkus
* Apache Camel
* Hibernate
* Flyway
* Agroal
* CDI 
* Resteasy 
* Rest Client 
* Rest Assured 
* Lombok
* Mapper Structs
* Postgres 
* Localstack (SQS)


## Running services locally

* To run locally suspending debug: 
`mvn quarkus:dev -Dsuspend=true -Ddebug=false`

* CURL: 
```
curl --location --request POST 'localhost:8080/ru' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        	"size": 7
        }'
```

## Docker (postgres, redis and localstack)

The docker compose with services used on this project is on directory: 
`./local/docker-compose.yml`

## Credit Analisys Integration

*Comming soon*

## Tests

*Comming soon*
