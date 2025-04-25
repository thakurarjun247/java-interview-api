learn:

learn how proxy classes created to give implementation to repository
diff between post mapping put mapping and names of all mappings, idempotency
rebase and stashing

todo:
global exception handling
add lombok and get rid of getters and setters
create a script to automate git push

curls:git 

curl -X POST http://localhost:8081/signup \
     -H "Content-Type: application/json" \
     -d '{
           "email": "arjun@example.com",
           "password": "password123"
         }'
curl -X GET "http://localhost:8081/find-by-email?email=arjun@example.com"
