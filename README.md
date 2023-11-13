# API WEATHER BY LATITUDE AND LONGITUDE

This API provides information such as maximum temperature, minimum temperature, and humidity based on latitude and longitude coordinates.

## Installation

### Requirements

- Spring Boot 3.1.5
- Java 17
- Gradle 7
- Minikube or any cloud service with Kubernetes
- kubectl
- Postman or any API testing tool

## Instructions for Starting the API

1. Clone this repository: [https://github.com/kevinjoe2/eval-promerica-kch.git](https://github.com/kevinjoe2/eval-promerica-kch.git)
2. Diagram arquitecture API in file DiagramEvalPromericaKch.drawio
3. In the root directory of the repository, you will find a `deployment.yaml` file for building the necessary deployment and service to use the API. 
4. In the deployment, there is a pod with two containers (API + DB (PostgreSQL)) and a service with type LoadBalancer.
5. The Docker Hub images can be found with the names:
   - `kechodev/eval_promerica_kch_api:latest`
   - `kechodev/eval_promerica_kch_db:latest`

### Testing the API

- To test the API, you can find a Postman file `eval-promerica.postman_collection.json` in the root repository. This file includes curl commands to check the API.
- Authentication is based on a bearer token.
- By default, there is a user with email: `joel@hotmail.com` and password: `password`, with the role `ADMIN` providing access to all endpoints.
- For generate token with user joel@hotmail.com (role ADMIN) use this endpoint: `
``curl --location 'http://localhost:8080/api/v1/auth/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "joel@hotmail.com",
    "password": "password"
}'``
- For create new user you have authenticate with user ADMIN, use this endpoint (Use your token): `
``curl --location 'http://localhost:8080/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2VsQGhvdG1haWwuY29tIiwiaWF0IjoxNjk5ODU4MDg0LCJleHAiOjE2OTk4NjE2ODR9.MyCmkZY1ST1XT89Ot5FRuaQ82SEPDsnil6D5VliRfs0' \
--data-raw '{
    "firstname": "Joel",
    "lastname": "Chamorro",
    "email": "joel@hotmail.com",
    "password": "password",
    "role": "ADMIN"
}'``
- For get weather use this endpoint (Use your token): `
``curl --location 'http://localhost:8080/api/v1/weather' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2VsQGhvdG1haWwuY29tIiwiaWF0IjoxNjk5ODU4MDg0LCJleHAiOjE2OTk4NjE2ODR9.MyCmkZY1ST1XT89Ot5FRuaQ82SEPDsnil6D5VliRfs0' \
--data '{
    "lat": -0.19263346869805648,
    "lon": -78.51194278681906
}'``
- For get weather history use this endpoint (Use your token):
``curl --location 'http://localhost:8080/api/v1/weather/history' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2VsQGhvdG1haWwuY29tIiwiaWF0IjoxNjk5ODU4MDg0LCJleHAiOjE2OTk4NjE2ODR9.MyCmkZY1ST1XT89Ot5FRuaQ82SEPDsnil6D5VliRfs0'``
