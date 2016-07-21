package com.testdev.videostorage.dao.impl;

import com.testdev.videostorage.dao.VideoDao;
import com.testdev.videostorage.domain.Video;

import java.util.List;

/**
 * @author oleh.krupenia.
 */
public class VideoDaoImpl implements VideoDao {
    @Override
    public List<Video> getVideosForUser(Long userId) {
        return null;
    }

    @Override
    public List<Video> searchVideosByTitle(String title) {
        return null;
    }

    @Override
    public boolean insertVideo(Video video) {
        return false;
    }

    @Override
    public boolean deleteVideo(Video video) {
        return false;
    }
}
