package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;
import com.thoughtworks.ketsu.infrastructure.validators.UserValidator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
        return Response.created(routes.userUrl(null)).build();
    }
}
