package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
        user.placeOrder(orderInfo);
        return Response.created(routes.orderUrl(user.getId(), Long.valueOf(orderInfo.get("id").toString()))).build();
    }
}
