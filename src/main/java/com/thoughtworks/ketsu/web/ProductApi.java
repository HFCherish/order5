package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.validators.ProductValidator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("products")
public class ProductApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map productInfo,
                           @Context Routes routes) {
        new ProductValidator().validate(productInfo);
        return Response.created(routes.productUrl(null)).build();
    }
}
