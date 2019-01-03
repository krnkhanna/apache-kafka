package org.spituk.study.kafka.java.examples.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.spituk.study.kafka.java.examples.vo.AccountNumber;

import java.util.Map;

/**
 * An example Kakfa serializer that converts {@link AccountNumber} to byte array.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/26/2018
 */
public class AccountNumberSerializer implements Serializer<AccountNumber> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
  }

  @Override
  public byte[] serialize(String topic, AccountNumber data) {
    byte[] retVal = null;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      retVal = objectMapper.writeValueAsString(data).getBytes();
    } catch (Exception exception) {
      System.out.println("Error in serializing object" + data);
    }
    return retVal;
  }

  @Override
  public void close() {
  }
}
