package org.spituk.study.kafka.java.examples.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.spituk.study.kafka.java.examples.vo.AccountNumber;

import java.util.Map;

/**
 * An example Kakfa deserializer that converts byte array to {@link AccountNumber}.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/26/2018
 */
public class AccountNumberDeserializer implements Deserializer<AccountNumber> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
  }

  @Override
  public AccountNumber deserialize(String topic, byte[] data) {
    ObjectMapper mapper = new ObjectMapper();
    AccountNumber object = null;
    try {
      object = mapper.readValue(data, AccountNumber.class);
    } catch (Exception exception) {
      System.out.println("Error in deserializing bytes " + exception);
    }
    return object;
  }

  @Override
  public void close() {
  }

}
