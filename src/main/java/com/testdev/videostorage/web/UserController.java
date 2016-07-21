package com.testdev.videostorage.web;

import com.testdev.videostorage.dao.UserDao;
import com.testdev.videostorage.dao.impl.UserDaoImpl;
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
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUser")
    public User getAuthorizedUser(@Context HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return Response.status(200).build();
    }

    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user, @Context HttpServletRequest request) {
        if (this.userDao.insertUser(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        return Response.status(200).build();
    }

    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User formData, @Context HttpServletRequest request) {
        User user = this.userDao.getUserByEmailAndPassword(formData.getEmail(), formData.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return Response.status(200).build();
    }
}
