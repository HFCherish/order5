package com.thoughtworks.ketsu.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("products")
public class ProductApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create() {
        return Response.created(URI.create("")).build();
    }
}
