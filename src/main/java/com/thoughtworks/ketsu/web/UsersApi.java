package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;
import com.thoughtworks.ketsu.infrastructure.validators.UserValidator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("users")
public class UsersApi {
    @Context
    UserRepository userRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Map userInfo,
                             @Context Routes routes) {
        new UserValidator().validate(userInfo);
        userRepository.save(userInfo);
        return Response.created(routes.userUrl(userInfo.get("id").toString())).build();
    }

    @Path("{id}")
    public UserApi getUser(@PathParam("id") long id) {
        return userRepository.findById(id)
                .map(UserApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

}
