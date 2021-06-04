# REST API Employee

## Install

    mvn install

## Run the app

    mvn spring-boot:run


# REST API

The REST API to the Employee app is described below.

## Get list of Employees

### Request

`GET http://localhost:8080/employees`

    curl -i -H 'Accept: application/json' http://localhost:8080/employees

### Response

    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Fri, 04 Jun 2021 07:00:52 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive

    [{
        "id": 1,
        "name": "Putterill",
        "surname": "Keslake",
        "salary": 7324.00
    },
    {
        "id": 2,
        "name": "Aldwich",
        "surname": "Fillon",
        "salary": 5898.00
    },
    ...
    ]

## Get Employee by id

### Request

`GET http://localhost:8080/employees/{id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/employees/1

### Response

    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Fri, 04 Jun 2021 07:05:44 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    
    {
        "id": 1,
        "name": "Putterill",
        "surname": "Keslake",
        "salary": 7324.00
    }
    
    Response code: 200; Time: 93ms; Content length: 64 bytes

## Get Employee by id not exist

### Request

`GET http://localhost:8080/employees/{id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/employees/9999

### Response


    HTTP/1.1 404
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Fri, 04 Jun 2021 09:02:34 GMT
    
    {
        "errorId":100,
        "timestamp":"2021-06-04T13:02:34.9795081",
        "errorCode":"unknown_employee",
        "errorMessage":"Employee not found by id: 11111"
    }
    

## Create a new Employee

### Request

`POST http://localhost:8080/employees`

    curl -i -X POST -H "Content-Type: application/json" -d "{\"name\":\"Ali\",\"surname\":\"Aliyev\",\"salary\":1700}" http://localhost:8080/employees


                                
### Response  

    HTTP/1.1 201
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Fri, 04 Jun 2021 09:00:32 GMT
    
    {
        "id":1001,
        "name":"Ali",
        "surname":"Aliyev",
        "salary":1700
    }


## Update employee

### Request

`PUT http://localhost:8080/employees/2`

    curl -i -H "Content-Type: application/json" -X PUT -d "{\"name\":\"Ali\",\"surname\":\"Aliyev\",\"salary\":1700}" http://localhost:8080/employees/2

### Response
    
    HTTP/1.1 200
    Content-Length: 0
    Date: Fri, 04 Jun 2021 12:12:46 GMT

## Delete Employee by id

### Request

`DELETE http://localhost:8080/employees/1`

    curl -i -H 'Accept: application/json' -X DELETE -d'_method=DELETE' http://localhost:8080/employees/1

### Response

    TTP/1.1 200
    Content-Length: 0
    Date: Fri, 04 Jun 2021 12:00:41 GMT


## Try to delete same Employee again

### Request

`DELETE http://localhost:8080/employees/1`

    curl -i -H 'Accept: application/json' -X DELETE -d'_method=DELETE' http://localhost:8080/employees/1

### Response

    HTTP/1.1 404
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Fri, 04 Jun 2021 12:01:09 GMT

    {"errorId":100,"timestamp":"2021-06-04T16:01:09.6821953","errorCode":"unknown_employee","errorMessage":"Employee not found by id: 1"}


## Get list of Employees

### Request

`GET http://localhost:8080/employees/list?pageNum=0&pageSize=10&sortBy=name&sortDir=asc&filter=Aqil`

    curl -i -H 'Accept: application/json' http://localhost:8080/employees/list?pageNum=0&pageSize=10&sortBy=name&sortDir=asc&filter=Aqil

### Response

    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Fri, 04 Jun 2021 17:16:53 GMT
                                                 
    {
    "content": [
        {
            "id": 1002,
            "name": "Aqil",
            "surname": "Aliyev",
            "salary": 2323.00
        },
        {
            "id": 1001,
            "name": "Aqil",
            "surname": "Zeka",
            "salary": 7287.00
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
    }
