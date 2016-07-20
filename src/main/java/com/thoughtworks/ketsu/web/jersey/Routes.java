package com.thoughtworks.ketsu.web.jersey;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = "/";
    }

    public URI productUrl(String prodId) {
        return URI.create(baseUri + "products/" + prodId);
    }

    public URI userUrl(String userId) {
        return URI.create(baseUri + "users/" + userId);
    }

    public URI orderUrl(long userId, long orderId) {
        return URI.create(baseUri + "users/" + userId + "/orders/" + orderId);
    }
}
