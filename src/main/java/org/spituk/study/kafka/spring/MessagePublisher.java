package org.spituk.study.kafka.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * The HelloWorld program implements an application that simply displays "Hello World!" to the
 * standard output.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/22/2018
 */
@Service
public class MessagePublisher {

  static String topicName = "test";

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String msg) {
    kafkaTemplate.send(topicName, msg);
  }

}
