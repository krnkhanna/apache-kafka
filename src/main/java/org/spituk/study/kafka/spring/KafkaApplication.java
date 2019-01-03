package org.spituk.study.kafka.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * The HelloWorld program implements an application that simply displays "Hello World!" to the
 * standard output.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/23/2018
 */
@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

  @Autowired
  MessagePublisher messagePublisher;

  public static void main(String[] args) {
    SpringApplication.run(KafkaApplication.class, args);
  }

  @Override
  public void run(String... args) {
    System.out.println("Method called");
    while (true) {
      messagePublisher.sendMessage("helllo there.");
    }
  }

  @KafkaListener(topics = "test", groupId = "foo")
  public void listen(String message) {
    System.out.println("Received message in group foo: " + message);
  }
}
