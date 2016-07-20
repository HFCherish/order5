package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.validators.UserValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

@Path("users")
public class UsersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Map userInfo) {
        new UserValidator().validate(userInfo);
        return Response.created(URI.create("")).build();
    }
}
