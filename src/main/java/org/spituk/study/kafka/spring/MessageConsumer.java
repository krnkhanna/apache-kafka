package org.spituk.study.kafka.spring;

import org.springframework.kafka.annotation.KafkaListener;

/**
 * The HelloWorld program implements an application that simply displays "Hello World!" to the
 * standard output.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/22/2018
 */
public class MessageConsumer {

  @KafkaListener(topics = "test", groupId = "foo")
  public void listen(String message) {
    System.out.println("Received Messasge in group foo: " + message);
  }

}
