package com.tutorial.starter.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Payment {
    private Long payment_id;
    private String sender_vpa;
    private Long amount;
    private String receiver_vpa;
    private Status STATUS;
    private Date initiated;
    private Date last_updated;
}
