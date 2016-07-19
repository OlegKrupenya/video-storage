package com.testdev.videostorage.web;

import com.testdev.videostorage.domain.User;

import javax.ws.rs.*;
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

    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(User user) {
        return Response.status(200).entity("done").build();
    }
}
