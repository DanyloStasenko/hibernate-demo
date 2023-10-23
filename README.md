Demo project to refresh Hibernate skills

Instructions:
1. Run Postgres in Docker:
   docker run --name testDB -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=postgresDB -d postgres
2. Insert data:
   cat dump_23-10-2023_12_05_27.sql | docker exec -i eb90cba4fa3f psql -U user postgresDB
3. Call via web-browser:
   _________________ Example 1 - N+1 Problem + fixes for typedQuery, nativeQuery _________________
[Optional] POST http://localhost:8080/posts   (create post)
   GET http://localhost:8080/np1/postComments
   GET http://localhost:8080/fixedNp1/postComments

   GET http://localhost:8080/np1/postComments/typedQuery
   GET http://localhost:8080/fixedNp1/postComments/typedQuery

   GET http://localhost:8080/np1/postComments/nativeQuery (same as in service, but used native @Query here)
   GET http://localhost:8080/fixedNp1/postComments/nativeQuery
   _________________ Example 2 - @EntityGraph _________________
   POST http://localhost:8080/users  (create a user)
   GET http://localhost:8080/np1/users/name (get users by 'name', n+1 problem detected if there is no "@EntityGraph" annotation)
   GET http://localhost:8080/np1fixed/users/name (N+1 issue fixed)

_________________
NOTES:
Dump Postgres data: 
   docker exec -t 3949c25ca778 pg_dumpall -c -U user > dump_`date +%d-%m-%Y"_"%H_%M_%S`.sql
Login inside container:
   docker exec -it 967e89002144 sh
   psql -U user -W postgresDB
   password 
   \dt (show tables)