package com.tutorial.starter.resources;

import com.tutorial.starter.api.Status;
import com.tutorial.starter.dao.PaymentDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jdbi.v3.core.Jdbi;

@Path("/payment/{payment_id}")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    Jdbi jdbi;

    public PaymentResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    public Response getPayment(@PathParam("payment_id") long payment_id) {
        return Response.ok(jdbi.withExtension(PaymentDao.class, dao -> dao.getPaymentById(payment_id))).build();
    }

    @POST
    public Response updatePayment(@PathParam("payment_id") long payment_id, @QueryParam("status") Status status) {
        int rowCount;
        if (status.equals(Status.PROCESSING)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Status of a payment cannot be updated to PROCESSING.").build();
        } else if (status.equals(Status.SUCCESSFUL)) {
            rowCount = jdbi.withExtension(PaymentDao.class, dao -> dao.updatePaymentAsSuccessful(payment_id));
        } else if (status.equals(Status.FAILED)) {
            rowCount = jdbi.withExtension(PaymentDao.class, dao -> dao.updatePaymentAsFailed(payment_id));
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Status of type: " + status + " lacks handling.").build();
        }

        if (rowCount == 0) {
            // No rows were updated, indicating that the payment with the specified ID was not found
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Payment with ID " + payment_id + " not found.")
                    .build();
        }

        // Payment status updated successfully
        return Response.status(Response.Status.OK)
                .entity("Payment with ID " + payment_id + " updated successfully.")
                .build();
    }
}
