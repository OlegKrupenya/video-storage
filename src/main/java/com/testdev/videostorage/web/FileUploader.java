package com.testdev.videostorage.web;

import com.testdev.videostorage.dao.VideoDao;
import com.testdev.videostorage.dao.impl.VideoDaoImpl;
import com.testdev.videostorage.domain.User;
import com.testdev.videostorage.domain.Video;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author oleh.krupenia.
 */
@WebServlet("/upload")
@MultipartConfig
public class FileUploader extends HttpServlet {

    private VideoDao videoDao = new VideoDaoImpl();

    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            byte[] bytes = getBytesFromInputStream(part.getInputStream());
            Video video = new Video();
            video.setTitle(fileName);
            video.setContent(bytes);
            video.setUserId(user.getUserId());
            this.videoDao.insertVideo(video);
        }
        response.sendRedirect("videos.html");
    }

    private byte[] getBytesFromInputStream(InputStream is) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[0xFFFF];
            for (int len; (len = is.read(buffer)) != -1; )
                os.write(buffer, 0, len);
            os.flush();
            return os.toByteArray();
        }
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}

