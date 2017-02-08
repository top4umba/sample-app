# sample-app

It is a microservice which can add/update an employee record {id: 10, name: ‘Mike’}

You can start the application with the following command:
```
./gradlew build && java -jar build/libs/sample-app-0.1.0.jar
```

To test a work you can use curl:
```
 curl -H "Content-Type: application/json" -X POST -d '{"name":"Mikhail"}' http://localhost:8080/api/employee
 
 curl http://localhost:8080/api/employee/1
 
 curl -H "Content-Type: application/json" -X PUT -d '{"name":"Not Mikhail"}' http://localhost:8080/api/employee/put/1
 
 curl http://localhost:8080/api/employee/1
```
Look that the name was updated. 
