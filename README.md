# Java RMI
Java Remote Method Invocation.
Application client-server.

## Client
The client sends a request to central server asking for the prediction of the horoscope and the weather.

## Server
There are 3 servers: central server, horoscope server and weather server.
A central server recive the request. If the server has the answer in the cache, it returns the answer, otherwise it consults the appropriate server.

## Run
build 
```sh
$ javac *.java
$ cd servidor
$ rmiregistry 54321 &
```

Permissions

Both in the client and server we must guarantee the permissions creating the files
cliente.permisos y servidor.permisos with the follow content:

grant codeBase "file:XXX...." {
    permission java.security.AllPermission;
};

where XXX is replace by the path of the apps


We finally proceed to the execution of the server:
```sh
$ cd servidor
$ java -Djava.security.policy=servidor.permisos  ServidorEco 54321
```

and the client:
```sh
cd cliente
java -Djava.security.policy=cliente.permisos  ClienteEco localhost 54321 hola adios
```
Result:
```sh
HOLA
ADIOS
```

Test:
```sh
$java -Djava.rmi.server.hostname=127.0.0.1 ServidorEco 54321
$java -Djava.security.policy=servidor.permisos ServidorEco 54321
```
