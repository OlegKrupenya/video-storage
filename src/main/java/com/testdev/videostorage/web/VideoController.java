package com.testdev.videostorage.web;

import com.testdev.videostorage.dao.VideoDao;
import com.testdev.videostorage.dao.impl.VideoDaoImpl;
import com.testdev.videostorage.domain.User;
import com.testdev.videostorage.domain.Video;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author oleh.krupenia.
 */
@Path("video")
public class VideoController {
    private VideoDao videoDao = new VideoDaoImpl();

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getVideosForUser")
    public List<Video> getVideosForUser(@Context HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return this.videoDao.getVideosForUser(user.getUserId());
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/removeVideos")
    public boolean removeVideos(Video[] videos, @Context HttpServletRequest request) {
        boolean result = true;
        for (Video video : videos) {
            if (!this.videoDao.deleteVideo(video)) {
                result = false;
            }
        }
        return result;
    }
}
