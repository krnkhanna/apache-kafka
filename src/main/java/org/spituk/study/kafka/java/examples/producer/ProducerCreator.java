package org.spituk.study.kafka.java.examples.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.spituk.study.kafka.java.examples.serializer.AccountNumberSerializer;
import org.spituk.study.kafka.java.examples.serializer.TransactionSerializer;
import org.spituk.study.kafka.java.examples.vo.AccountNumber;
import org.spituk.study.kafka.java.examples.partitioner.CustomPartitioner;
import org.spituk.study.kafka.java.examples.vo.Transaction;

import java.util.Properties;

/**
 * An example factory class for creating {@link Producer}.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/25/2018
 */
public class ProducerCreator {

  private static String KAFKA_BROKERS = "localhost:9092";

  public static Producer<AccountNumber, Transaction> createProducer() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
    props.put(ProducerConfig.ACKS_CONFIG, "all");
    props.put(ProducerConfig.RETRIES_CONFIG, 0);
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, AccountNumberSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TransactionSerializer.class.getName());
    props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
    return new KafkaProducer<>(props);
  }
}
