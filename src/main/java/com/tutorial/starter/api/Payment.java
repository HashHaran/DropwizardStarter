package com.tutorial.starter.api;

import com.aerospike.mapper.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@AerospikeRecord(namespace = "starter", set = "payments")
public class Payment {
    @AerospikeKey
    private Long payment_id;
    private String sender_vpa;
    private Long amount;
    private String receiver_vpa;
    private Status STATUS;
    private Date initiated;
    private Date last_updated;

    public Payment() {
        this.STATUS = Status.PROCESSING;
        this.initiated = new Date();
        this.last_updated = new Date();
    }

    public Payment(String sender_vpa, Long amount, String receiver_vpa) {
        this.sender_vpa = sender_vpa;
        this.amount = amount;
        this.receiver_vpa = receiver_vpa;
        this.STATUS = Status.PROCESSING;
        this.initiated = new Date();
        this.last_updated = new Date();
    }

    public Payment(Long payment_id, String sender_vpa, Long amount, String receiver_vpa, Status STATUS, Date initiated, Date last_updated) {
        this.payment_id = payment_id;
        this.sender_vpa = sender_vpa;
        this.amount = amount;
        this.receiver_vpa = receiver_vpa;
        this.STATUS = STATUS;
        this.initiated = initiated;
        this.last_updated = last_updated;
    }

    @JsonProperty
    public Long getPayment_id() {
        return payment_id;
    }

    @JsonProperty
    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    @JsonProperty
    public String getSender_vpa() {
        return sender_vpa;
    }

    @JsonProperty
    public void setSender_vpa(String sender_vpa) {
        this.sender_vpa = sender_vpa;
    }

    @JsonProperty
    public Long getAmount() {
        return amount;
    }

    @JsonProperty
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @JsonProperty
    public String getReceiver_vpa() {
        return receiver_vpa;
    }

    @JsonProperty
    public void setReceiver_vpa(String receiver_vpa) {
        this.receiver_vpa = receiver_vpa;
    }

    @JsonProperty
    public Status getSTATUS() {
        return STATUS;
    }

    @JsonProperty
    public void setSTATUS(Status STATUS) {
        this.STATUS = STATUS;
    }

    @JsonProperty
    public Date getInitiated() {
        return initiated;
    }

    @JsonProperty
    public void setInitiated(Date initiated) {
        this.initiated = initiated;
    }

    @JsonProperty
    public Date getLast_updated() {
        return last_updated;
    }

    @JsonProperty
    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }
}
