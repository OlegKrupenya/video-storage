package com.testdev.videostorage.dao;

import com.testdev.videostorage.domain.Video;

import java.util.List;

/**
 * @author oleh.krupenia.
 */
public interface VideoDao {
    List<Video> getVideosForUser(Long userId);
    Video getVideoById(Long videoId);
    List<Video> searchVideosByTitle(String title);
    boolean insertVideo(Video video);
    boolean deleteVideo(Video video);
}
