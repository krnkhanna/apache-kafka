package org.spituk.study.kafka.java.examples.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents Transaction that will be used for this example as a value object from Kafka
 * record.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 12/26/2018
 */
public class Transaction implements Serializable {

  private float amount;

  private Date transactionDate;

  public Transaction() {

  }

  public Transaction(float amount, Date transactionDate) {
    this.amount = amount;
    this.transactionDate = new Date(transactionDate.getTime());
  }

  public float getAmount() {
    return amount;
  }

  public Date getTransactionDate() {
    return new Date(transactionDate.getTime());
  }

  @Override
  public String toString() {
    return "Transaction{" + "amount=" + amount + ", transactionDate=" + transactionDate + "}";
  }
}
