package com.testdev.videostorage.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author oleh.krupenia.
 */
@Path("hello")
public class UserController {
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    @Path("/plain")
    public String getPlain() {
        return "Hello World!!!";
    }
}
