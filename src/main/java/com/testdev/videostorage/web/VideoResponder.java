package com.testdev.videostorage.web;

import com.testdev.videostorage.dao.VideoDao;
import com.testdev.videostorage.dao.impl.VideoDaoImpl;
import com.testdev.videostorage.domain.Video;
import com.testdev.videostorage.utils.ConnectionManager;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author oleh.krupenia.
 */
@WebServlet("/video")
public class VideoResponder extends HttpServlet {
    private VideoDao videoDao = new VideoDaoImpl(ConnectionManager.getConnection());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String videoId = request.getParameter("videoId");
        if (videoId != null) {
            Video video = this.videoDao.getVideoById(Long.parseLong(videoId));
            if (video != null) {
                response.setContentType("video/mp4");
                try (ServletOutputStream out = response.getOutputStream();
                     ByteArrayInputStream bis = new ByteArrayInputStream(video.getContent());) {
                    byte[] buf = new byte[4096];
                    int read;
                    while ((read = bis.read(buf)) != -1) {
                        out.write(buf, 0, read);
                    }
                }
            }
        }
    }
}
