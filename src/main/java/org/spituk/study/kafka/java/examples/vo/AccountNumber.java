package org.spituk.study.kafka.java.examples.vo;

import java.io.Serializable;

/**
 * This class represents Account Number that will be used for this example as a key object from
 * Kafka record.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/26/2018
 */
public class AccountNumber implements Serializable {

  private long accountNumber;

  public AccountNumber() {

  }

  public AccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public long getAccountNumber() {
    return accountNumber;
  }

  @Override
  public String toString() {
    return "AccountNumber{" + "accountNumber=" + accountNumber + "}";
  }
}
