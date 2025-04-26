# AuthService
Once of the most crucial microservice of the project freedom.

https://www.youtube.com/watch?v=yodeo205pp0&t=84s

### curl for endpoints

curl -X POST http://localhost:8081/signup \
-H "Content-Type: application/json" \
-d '{
"email": "arjun@example.com",
"password": "password123"
}'

curl -X GET "http://localhost:8081/find-by-email?email=arjun@example.com"

