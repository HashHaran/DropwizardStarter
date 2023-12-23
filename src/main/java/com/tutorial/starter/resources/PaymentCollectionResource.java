package com.tutorial.starter.resources;

import com.codahale.metrics.annotation.Timed;
import com.tutorial.starter.api.Payment;
import com.tutorial.starter.dao.PaymentDao;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jdbi.v3.core.Jdbi;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentCollectionResource {

    Jdbi jdbi;

    public PaymentCollectionResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    @Timed
    public Response getPayments(@QueryParam("sender") String sender,
                                     @QueryParam("receiver") String receiver,
                                     @QueryParam("minAmount") Long minAmount,
                                     @QueryParam("maxAmount") Long maxAmount) {
        if (minAmount == null) {
            minAmount = Long.MIN_VALUE;
        }
        if (maxAmount == null) {
            maxAmount = Long.MAX_VALUE;
        }
        Long finalMinAmount = minAmount;
        Long finalMaxAmount = maxAmount;
        if (sender == null && receiver == null) {
            return Response.ok(jdbi.withExtension(PaymentDao.class, dao -> dao.listPayments(finalMinAmount, finalMaxAmount))).build();
        } else  if (sender == null) {
            return Response.ok(jdbi.withExtension(PaymentDao.class, dao -> dao.getPaymentToUser(receiver, finalMinAmount, finalMaxAmount))).build();
        } else if (receiver == null) {
            return Response.ok(jdbi.withExtension(PaymentDao.class, dao -> dao.getPaymentByUser(sender, finalMinAmount, finalMaxAmount))).build();
        } else {
            return Response.ok(jdbi.withExtension(PaymentDao.class, dao -> dao.getPaymentByAndToUser(sender, receiver, finalMinAmount, finalMaxAmount))).build();
        }
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(@Valid Payment payment) {
        return Response.ok(jdbi.withExtension(PaymentDao.class, dao -> dao.insertNewPayment(payment))).build();
    }

}
