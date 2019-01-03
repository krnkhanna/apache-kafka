package org.spituk.study.kafka.java.examples.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.spituk.study.kafka.java.examples.vo.AccountNumber;
import org.spituk.study.kafka.java.examples.vo.Transaction;

/**
 * An example class for fetching records from the Kafka cluster.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/26/2018
 */
public class KafkaConsumer {

  private static Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;

  public static void main(String[] args) {
    runConsumer();
  }

  static void runConsumer() {
    Consumer<AccountNumber, Transaction> consumer = ConsumerCreator.createConsumer();
    int noMessageFound = 0;
    while (true) {
      ConsumerRecords<AccountNumber, Transaction> consumerRecords = consumer.poll(1000);
      // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
      if (consumerRecords.count() == 0) {
        noMessageFound++;
        if (noMessageFound > MAX_NO_MESSAGE_FOUND_COUNT)
        // If no message found count is reached to threshold exit loop.
        {
          break;
        } else {
          continue;
        }
      }
      //print each record.
      for (ConsumerRecord record : consumerRecords) {
        System.out.println("Record Key " + record.key());
        System.out.println("Record value " + record.value());
        System.out.println("Record partition " + record.partition());
        System.out.println("Record offset " + record.offset());
      }

      // commits the offset of record to broker.
      consumer.commitAsync();
    }
    consumer.close();
  }


}
