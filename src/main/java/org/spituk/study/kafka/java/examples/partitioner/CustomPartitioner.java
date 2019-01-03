package org.spituk.study.kafka.java.examples.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.spituk.study.kafka.java.examples.vo.AccountNumber;

import java.util.Map;

/**
 * An example Kakfa partitioner that determines the partition number from the key for Kafka record.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/26/2018
 */
public class CustomPartitioner implements Partitioner {

  private static final int PARTITION_COUNT = 2;

  @Override
  public void configure(Map<String, ?> configs) {
  }

  @Override
  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
      Cluster cluster) {
    AccountNumber accountNumber = (AccountNumber) key;
    return (int) accountNumber.getAccountNumber() % PARTITION_COUNT;
  }

  @Override
  public void close() {
  }
}
