# Oracle Workflow

## Setup Guide

- Start Mysql Server
- Create a database `workflow` -> `create database workflow`
- check the username and password and update the same in `application-dev.properties` file

## Running the Application : 

- `gradle bootrun` 
- port is 8080 -> `http://localhost:8080`   


## API Documentation:

### Workflow mappings:

#### Create a new Party Request
```
curl --location --request POST 'http://localhost:8080/createParty' \
--header 'Content-Type: application/json' \
--data-raw '{
    
    "userName":"nitin",
    "companyName": "D",
    "address" : "kanpur"
}'
  ```
##### Sample Response 
```
{
    "message": "Successfully Processed the Create Party Request",
    "links": {
        "REJECT": "http://localhost:8080/approval?id=4&status=REJECT",
        "APPROVE": "http://localhost:8080/approval?id=4&status=APPROVE"
    },
    "statusCode": 200
}
```
#### Sample validation Error Response

```
Status Code : 400
Duplicate Entity Exists  
```

### Party Mappings 

#### Get Party Status List
```
GET -> http://localhost:8080/partyStatusList?page=0&size=10
```

##### Sample Response
```
// 20201205190230
// http://localhost:8080/partyStatusList

{
  "content": [
    {
      "id": 1,
      "partyCompositeId": {
        "userName": "nitin",
        "companyName": "A"
      },
      "authorizedApprovers": "nitin1494gupta@gmail.com",
      "companyId": 7474,
      "address": "kanpur",
      "status": "NOT APPROVED",
      "approver": null,
      "createdDate": "2020-12-05T13:08:17.000+00:00",
      "updatedDate": "2020-12-05T13:08:17.000+00:00",
      "updatedBy": "nitin",
      "createdBy": "nitin"
    },
    {
      "id": 2,
      "partyCompositeId": {
        "userName": "nitin",
        "companyName": "B"
      },
      "authorizedApprovers": "nitin1494gupta@gmail.com",
      "companyId": 7474,
      "address": "kanpur",
      "status": "NOT APPROVED",
      "approver": null,
      "createdDate": "2020-12-05T13:14:25.000+00:00",
      "updatedDate": "2020-12-05T13:14:25.000+00:00",
      "updatedBy": "nitin",
      "createdBy": "nitin"
    },
    {
      "id": 3,
      "partyCompositeId": {
        "userName": "nitin",
        "companyName": "C"
      },
      "authorizedApprovers": "nitin1494gupta@gmail.com",
      "companyId": 7474,
      "address": "kanpur",
      "status": "NOT APPROVED",
      "approver": null,
      "createdDate": "2020-12-05T13:20:13.000+00:00",
      "updatedDate": "2020-12-05T13:20:13.000+00:00",
      "updatedBy": "nitin",
      "createdBy": "nitin"
    },
    {
      "id": 4,
      "partyCompositeId": {
        "userName": "nitin",
        "companyName": "D"
      },
      "authorizedApprovers": "nitin1494gupta@gmail.com",
      "companyId": 7474,
      "address": "kanpur",
      "status": "APPROVED",
      "approver": "nitin1494gupta@gmail.com",
      "createdDate": "2020-12-05T13:21:05.000+00:00",
      "updatedDate": "2020-12-05T13:21:37.000+00:00",
      "updatedBy": "nitin1494gupta@gmail.com",
      "createdBy": "nitin"
    }
  ],
  "pageable": {
    "sort": {
      "unsorted": true,
      "sorted": false,
      "empty": true
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 4,
  "last": true,
  "numberOfElements": 4,
  "first": true,
  "sort": {
    "unsorted": true,
    "sorted": false,
    "empty": true
  },
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### Get Party Requested List

```
http://localhost:8080/partyList?page=0&size=10
```
##### Sample Response
```
// 20201205190416
// http://localhost:8080/partyList

{
  "content": [
    {
      "partyCompositeId": {
        "userName": "nitin",
        "companyName": "A"
      },
      "id": 6,
      "companyId": 7474,
      "address": "kanpur",
      "meta": null,
      "createdDate": "2020-12-05T13:08:17.000+00:00",
      "updatedDate": "2020-12-05T13:08:17.000+00:00"
    },
    {
      "partyCompositeId": {
        "userName": "nitin",
        "companyName": "B"
      },
      "id": 7,
      "companyId": 7474,
      "address": "kanpur",
      "meta": null,
      "createdDate": "2020-12-05T13:14:25.000+00:00",
      "updatedDate": "2020-12-05T13:14:25.000+00:00"
    },
    {
      "partyCompositeId": {
        "userName": "nitin",
        "companyName": "C"
      },
      "id": 8,
      "companyId": 7474,
      "address": "kanpur",
      "meta": null,
      "createdDate": "2020-12-05T13:20:12.000+00:00",
      "updatedDate": "2020-12-05T13:20:12.000+00:00"
    },
    {
      "partyCompositeId": {
        "userName": "nitin",
        "companyName": "D"
      },
      "id": 9,
      "companyId": 7474,
      "address": "kanpur",
      "meta": null,
      "createdDate": "2020-12-05T13:21:05.000+00:00",
      "updatedDate": "2020-12-05T13:21:05.000+00:00"
    }
  ],
  "pageable": {
    "sort": {
      "unsorted": true,
      "sorted": false,
      "empty": true
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 4,
  "last": true,
  "numberOfElements": 4,
  "first": true,
  "sort": {
    "unsorted": true,
    "sorted": false,
    "empty": true
  },
  "size": 10,
  "number": 0,
  "empty": false
}
```
