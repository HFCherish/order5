package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.infrastructure.validators.OrderValidator;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.apache.ibatis.annotations.Param;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class OrdersApi {
    private User user;

    public OrdersApi(User user) {
        this.user = user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder(Map orderInfo,
                               @Context Routes routes) {
        new OrderValidator().validate(orderInfo);
        user.placeOrder(orderInfo);
        return Response.created(routes.orderUrl(user.getId(), Long.valueOf(orderInfo.get("id").toString()))).build();
    }

    @Path("{id}")
    public OrderApi getOrder(@PathParam("id") long id) {
        return user.getOrderById(id)
                .map(OrderApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
