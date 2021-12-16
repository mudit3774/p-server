About
=====

p-server listens to alerts from [p-agent](https://github.com/mudit3774/p-agent) and provides a search API over it

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

Create some alerts (convinience API for testing)

`
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
}
`

This should return 200 OK.

Search for all alerts :

`
curl -X GET \
  http://localhost:8080/v1/alert/search \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'postman-token: 30122964-c12b-abe1-030e-50a97f6dbf82'
`

and this should return alerts


`
[
    {
        "host": "H7",
        "app": "B",
        "maskedLine": null,
        "timestamp": 5,
        "filename": "sdsj"
    },
    {
        "host": "H8",
        "app": "B",
        "maskedLine": null,
        "timestamp": 20,
        "filename": "sdsj"
    }
]
`
Driver Program (TODO : Evolve into integration test)
==============

<script src="https://gist.github.com/mudit3774/f10af3b334b500b1b63344916ba83a72.js"></script>
