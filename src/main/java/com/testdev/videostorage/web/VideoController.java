package com.testdev.videostorage.web;

import com.testdev.videostorage.dao.VideoDao;
import com.testdev.videostorage.dao.impl.VideoDaoImpl;
import com.testdev.videostorage.domain.User;
import com.testdev.videostorage.domain.Video;
import com.testdev.videostorage.utils.ConnectionManager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

/**
 * @author oleh.krupenia.
 */
@Path("video")
public class VideoController {
    private VideoDao videoDao = new VideoDaoImpl(ConnectionManager.getConnection());

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
        return Arrays.stream(videos)
                .map(video1 -> this.videoDao.deleteVideo(video1))
                .filter(b -> false).findAny().orElse(true);
    }
}
