package org.spituk.study.kafka.java.examples.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.spituk.study.kafka.java.examples.vo.AccountNumber;
import org.spituk.study.kafka.java.examples.vo.Transaction;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * An example class for sending records to the Kafka cluster.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/26/2018
 */
public class KafkaProducer {

  private static String TOPIC_NAME = "account";

  private static Integer MESSAGE_COUNT = 1000;

  public static void main(String[] args) {
    runProducer();
  }

  private static void runProducer() {
    Producer<AccountNumber, Transaction> producer = ProducerCreator.createProducer();
    for (int index = 0; index < MESSAGE_COUNT; index++) {
      ProducerRecord<AccountNumber, Transaction> record = new ProducerRecord<>(TOPIC_NAME, new
          AccountNumber(index), new Transaction(index, new Date()));
      try {
        RecordMetadata metadata = producer.send(record).get();
        System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
            + " with offset " + metadata.offset());
      } catch (ExecutionException e) {
        System.out.println("Error in sending record");
        System.out.println(e);
      } catch (InterruptedException e) {
        System.out.println("Error in sending record");
        System.out.println(e);
      }
    }
  }
}
