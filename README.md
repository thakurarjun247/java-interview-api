# AuthService
Once of the most crucial microservice of the project freedom.

https://www.youtube.com/watch?v=yodeo205pp0&t=84s

## curl for endpoints

### login
When you call /login, Spring Security is handling it internally.

Spring expects a form-data POST (application/x-www-form-urlencoded) by default — not JSON.

When you send JSON, Spring Security's default UsernamePasswordAuthenticationFilter doesn't understand it.
It is expecting form fields, not JSON fields.

curl -X POST http://localhost:8081/login \
-H "Content-Type: application/x-www-form-urlencoded" \
-d "username=arjun@example.com&password=password123"

### signup
curl -X POST http://localhost:8081/signup \
-H "Content-Type: application/json" \
-d '{
"email": "admin@gmail.com",
"password": "admin",
"userRoles": ["ADMIN"],
"authProvider": "LOCAL",
"userStatus": "ACTIVE",
"createdAt": "2025-04-26T00:00:00Z",
"updatedAt": "2025-04-26T00:00:00Z"
}'

### findbyemail
curl -X GET "http://localhost:8081/users?email=arjun@example.com"
### findAll
curl -X GET "http://localhost:8081/users"