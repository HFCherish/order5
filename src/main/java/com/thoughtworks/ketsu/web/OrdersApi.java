package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

public class OrdersApi {
    private User user;

    public OrdersApi(User user) {
        this.user = user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder() {
        return Response.created(URI.create("")).build();
    }
}
