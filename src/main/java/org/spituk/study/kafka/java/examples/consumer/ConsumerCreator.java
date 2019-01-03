package org.spituk.study.kafka.java.examples.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.spituk.study.kafka.java.examples.deserializer.AccountNumberDeserializer;
import org.spituk.study.kafka.java.examples.deserializer.TransactionDeserializer;
import org.spituk.study.kafka.java.examples.vo.AccountNumber;
import org.spituk.study.kafka.java.examples.vo.Transaction;

import java.util.Collections;
import java.util.Properties;

/**
 * An example factory class for creating {@link Consumer}.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/25/2018
 */
public class ConsumerCreator {

  private static String KAFKA_BROKERS = "localhost:9092";

  private static String GROUP_ID_CONFIG = "consumerGroup1";

  private static String TOPIC_NAME = "account";

  private static String CLIENT_ID = "client1";

  private static String OFFSET_RESET_LATEST = "latest";

  private static String OFFSET_RESET_EARLIER = "earliest";

  private static Integer MAX_POLL_RECORDS = 1;

  public static Consumer<AccountNumber, Transaction> createConsumer() {
    Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        AccountNumberDeserializer.class.getName());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        TransactionDeserializer.class.getName());
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL_RECORDS);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OFFSET_RESET_EARLIER);
    Consumer<AccountNumber, Transaction> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Collections.singletonList(TOPIC_NAME));

    //TopicPartition partition = new TopicPartition(TOPIC_NAME, 0);
    //consumer.assign(Collections.singletonList(partition));

    return consumer;
  }

}
