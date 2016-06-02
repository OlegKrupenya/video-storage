package com.testdev.videostorage.web;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author oleh.krupenia.
 */
@WebServlet("/video")
public class VideoResponder extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("video/mp4");
        ServletOutputStream out = response.getOutputStream();
        FileInputStream fin = new FileInputStream("C:\\Users\\Public\\Videos\\Sample Videos\\small.ogg");

        byte[] buf = new byte[4096];
        int read;
        while ((read = fin.read(buf)) != -1) {
            out.write(buf, 0, read);
        }

        fin.close();
        out.flush();
        out.close();
    }
}
