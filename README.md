# APACHE KAFKA

_This is my quick reference for Apache Kafka and its producer and consumer API's in Java and also with Spring framework._

**Contributions:** Issues, comments and pull requests are super welcome :smiley:

## 1. TABLE OF CONTENTS

- [1. TABLE OF CONTENTS](##1-table-of-contents)
- [2. RUNNING KAFKA ON WINDOWS](##2-running-kafka-on-windows)
- [3. TERMINOLOGY](##3-terminology)
- [4. KAFKA PRODUCER](##4-kafka-producer)

## 2. RUNNING KAFKA ON WINDOWS

### 1. Start Zookeeper

`C:\kafka>bin\windows\zookeeper-server-start.bat config\zookeeper.properties`

### 2. Start Kafka Server

`C:\kafka>bin\windows\kafka-server-start.bat config\server.properties`

### 3. Create a topic with name `test`, that has only one partition & one replica.

`C:\kafka>bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test`

### 4. Create a producer to send message to the above created `test` topic and send a message to it

`C:\kafka>bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test`

`>Hello World`

### 5. Start the consumer which listens to the topic `test` created above.

`C:\kafka>bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning`

## 3. TERMINOLOGY

### 1. Record

Producer sends messages to Kafka in the form of records. A record is a key-value pair. It contains the topic name and partition number to be sent. Kafka broker keeps records inside topic partitions. Records sequence is maintained at the partition level. You can define the logic on which basis partition will be determined.

### 2. Topic

Producer writes a record on a topic and the consumer listens to it. A topic can have many partitions but must have at least one.

### 3. Partition

A topic partition is a unit of parallelism in Kafka, i.e. two consumers cannot consume messages from the same partition at the same time. A consumer can consume from multiple partitions at the same time.

### 4. Offset

A record in a partition has an offset associated with it. Think of it like this: partition is like an array; offsets are like indexes.

### 5. Producer

Creates a record and publishes it to the broker.

### 6. Consumer

Consumes records from the broker.

## 4. KAFKA PRODUCER

A Kafka client that publishes records to Kafka cluster. The producer is `thread-safe` and sharing a single producer instance across threads is generally faster than having multiple instances.

[Example for creating a Kafka Producer.][1]

The producer consists of a pool of buffer space that holds records that haven't yet been transmitted to the server as well as a backgroud I/O thread that is responsible for turning these records into requests and transmitting them to the cluster. Failure to close the producer after use will leak these resources.

The `send()` method is asynchronus. When called it adds the record to a buffer of pending records and immediately returns. This allows the producer to batch together individual records for efficiency.

The `acks`config controls the criteria under which the requests are considered complete. The "all" setting results in blocking on the full commit of the record, is the slowest but most durable setting.

If the request fails the producer can automatically retry and `retries` specifies the number of times producer will retry. Enabling retries also opens up the possibilty of duplicates.

The `key.serializer` and `value.serializer` controls how to turn the key value objects the user provides with their `ProducerRecord` into bytes.

[Example for creating a key serializer.][2]

[Example for creating a value serializer.][3]

[1]: https://github.com/krnkhanna1989/apache-kafka/blob/master/src/main/java/org/spituk/study/kafka/java/examples/producer/ProducerCreator.java
[2]: https://github.com/krnkhanna1989/apache-kafka/blob/master/src/main/java/org/spituk/study/kafka/java/examples/serializer/AccountNumberSerializer.java
[3]: https://github.com/krnkhanna1989/apache-kafka/blob/master/src/main/java/org/spituk/study/kafka/java/examples/serializer/TransactionSerializer.java
