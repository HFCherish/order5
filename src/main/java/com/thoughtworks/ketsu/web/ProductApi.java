package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.validators.ProductValidator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("products")
public class ProductApi {
    @Context
    ProductRepository productRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map productInfo,
                           @Context Routes routes) {
        new ProductValidator().validate(productInfo);
        productRepository.save(productInfo);
        return Response.created(routes.productUrl(productInfo.get("id").toString())).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOne(@PathParam("id") long id) {
        return productRepository.findById(id)
                .map(product -> product)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {
        return new ArrayList<>();
    }
}
