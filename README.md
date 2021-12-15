About
=====

p-server listens to alerts create from p-agent and provides a search API over it

Requirement 
===========

* Kafka 2.12+ 
* Java 1.8+ 
* Gradle 4+

Setup
=====

* git clone https://github.com/mudit3774/p-server
* $> gradle clean build 
* $> ./gradlew bootRun

Testing
=======

Create some alerts (In production, alerts would be created though Kafka)

'
curl -X POST \
http://localhost:8080/alerts \
-H 'cache-control: no-cache' \
-H 'content-type: application/json; charset=utf8' \
-H 'postman-token: 0d122dd3-b734-f463-6f39-ab641a1f24fc' \
-d '{
"alerts" : [{
"host" : "H7",
"app" : "B",
"markedLine" : "ML",
"timestamp" : 5,
"filename" : "sdsj"
}, {
"host" : "H8",
"app" : "B",
"markedLine" : "ML",
"timestamp" : 20,
"filename" : "sdsj"
}]
}'

This should return 200 OK.

Search for all alerts :
'
curl -X GET \
http://localhost:8080/search \
-H 'cache-control: no-cache' \
-H 'content-type: application/x-www-form-urlencoded' \
-H 'postman-token: 8ef1a0bb-fb79-9826-b7e0-fc8884f533b5'
'