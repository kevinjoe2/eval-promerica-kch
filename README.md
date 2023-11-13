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
