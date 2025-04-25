# AuthService
Once of the most crucial microservice of the project freedom.



### curl for endpoints

curl -X POST http://localhost:8081/signup \
-H "Content-Type: application/json" \
-d '{
"email": "arjun@example.com",
"password": "password123"
}'

curl -X GET "http://localhost:8081/find-by-email?email=arjun@example.com"

