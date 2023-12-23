package com.tutorial.starter.dao;

import com.tutorial.starter.api.Payment;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
/*
Simple CRUD JDBI Declarative (SQL Objects) DAO
 */
public interface PaymentDao {

    //Create
    @SqlUpdate("INSERT INTO \"payment\" (sender_vpa, amount, receiver_vpa, \"STATUS\", initiated, last_updated) VALUES (:sender_vpa, :amount, :receiver_vpa, \"PROCESSING\", :initiated, :last_updated)")
    @GetGeneratedKeys
    long insertNewPayment(@BindBean Payment payment);

    //Read
    @SqlQuery("SELECT * FROM \"payment\" WHERE amount >= ? AND amount <= ?")
    @RegisterBeanMapper(Payment.class)
    List<Payment> listPayments(Long minAmount, Long maxAmount);

    @SqlQuery("SELECT * FROM \"payment\" WHERE payment_id = ?")
    @RegisterBeanMapper(Payment.class)
    Payment getPaymentById(Long payment_id);

    @SqlQuery("SELECT * FROM \"payment\" WHERE sender_vpa = ? AND amount >= ? AND amount <= ?")
    @RegisterBeanMapper(Payment.class)
    List<Payment> getPaymentByUser(String sender_vpa, Long minAmount, Long maxAmount);

    @SqlQuery("SELECT * FROM \"payment\" WHERE receiver_vpa = ? AND amount >= ? AND amount <= ?")
    @RegisterBeanMapper(Payment.class)
    List<Payment> getPaymentToUser(String receiver_vpa, Long minAmount, Long maxAmount);

    @SqlQuery("SELECT * FROM \"payment\" WHERE sender_vpa = ? AND receiver_vpa = ? AND amount >= ? AND amount <= ?")
    @RegisterBeanMapper(Payment.class)
    List<Payment> getPaymentByAndToUser(String sender_vpa, String receiver_vpa, Long minAmount, Long maxAmount);

    //Update
    @SqlUpdate("UPDATE \"payment\" SET \"STATUS\" = SUCCESS WHERE payment_id = ?")
    int updatePaymentAsSuccessful(Long payment_id);

    @SqlUpdate("UPDATE \"payment\" SET \"STATUS\" = FAILED WHERE payment_id = ?")
    int updatePaymentAsFailed(Long payment_id);

    //Delete
    //Not implementing delete as it does not make any sense to delete a payment record

}
