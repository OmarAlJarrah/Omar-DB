#!/bin/zsh
./kafka/bin/zookeeper-server-start.sh ./kafka/config/zookeeper.properties\
&\
./kafka/bin/kafka-server-start.sh ./kafka/config/server.properties\
&\
cd OmarDB\
&&\
mvn clean install package\
&&\
java -jar target/OmarDB-0.0.1-alpha.1.jar --nodeType=master --server.port=8080