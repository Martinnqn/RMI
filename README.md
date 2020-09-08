# Java RMI
Java Remote Method Invocation.
Application client-server.

## Client
The client sends a request to central server asking for the prediction of the horoscope and the weather.

## Server
There are 3 servers: central server, horoscope server and weather server.
A central server recive the request. If the server has the answer in the cache, it returns the answer, otherwise it consults the appropriate server.
