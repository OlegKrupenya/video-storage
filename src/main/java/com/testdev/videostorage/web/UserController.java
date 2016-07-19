package com.testdev.videostorage.web;

import com.testdev.videostorage.dao.UserDao;
import com.testdev.videostorage.dao.UserDaoImpl;
import com.testdev.videostorage.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author oleh.krupenia.
 */
@Path("hello")
public class UserController {
    private UserDao userDao = new UserDaoImpl();

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
    public Response consumeJSON(User user, @Context HttpServletRequest request) {
        if (this.userDao.insertUser(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        return Response.status(200).build();
    }
}
