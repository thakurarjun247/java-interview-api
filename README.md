# AuthService
Once of the most crucial microservice of the project freedom.

https://www.youtube.com/watch?v=yodeo205pp0&t=84s

## curl for endpoints

### login

curl -X POST http://localhost:8081/login \
-H "Content-Type: application/json" \
-d '{"email": "arjun@example.com", "password": "password123"}'

### signup
curl -X POST http://localhost:8081/signup \
-H "Content-Type: application/json" \
-d '{
"email": "arjun@gmail.com",
"password": "password123",
"userRoles": ["USER"],
"authProvider": "LOCAL",
"userStatus": "ACTIVE",
"createdAt": "2025-04-26T00:00:00Z",
"updatedAt": "2025-04-26T00:00:00Z"
}'

### findbyemail - deprecated
curl -X GET "http://localhost:8081/find-by-email?email=arjun@example.com"

