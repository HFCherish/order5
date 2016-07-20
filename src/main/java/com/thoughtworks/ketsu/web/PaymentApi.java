package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

public class PaymentApi {
    private Order order;

    public PaymentApi(Order order) {
        this.order = order;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(Map payInfo) {
        if(payInfo.get("pay_type") == null )
            throw new IllegalArgumentException("must contains pay_type.");
        if(payInfo.get("amount") == null )
            throw new IllegalArgumentException("must contains amount.");
        order.pay(payInfo);
        return Response.created(URI.create("")).build();
    }
}
