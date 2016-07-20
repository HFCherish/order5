package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.domain.Product;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = "/";
    }

    public URI productUrl(Product product) {
        return URI.create(baseUri + "products");
    }
}
