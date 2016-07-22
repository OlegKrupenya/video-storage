package com.testdev.videostorage.dao.impl;

import com.testdev.videostorage.dao.VideoDao;
import com.testdev.videostorage.domain.Video;
import com.testdev.videostorage.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oleh.krupenia.
 */
public class VideoDaoImpl implements VideoDao {
    private Connection connection = ConnectionManager.getConnection();

    @Override
    public List<Video> getVideosForUser(Long userId) {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT * FROM videos where user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            Video video = populateVideoFromResultSet(rs);
            if (video != null) {
                videos.add(video);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }

    private Video populateVideoFromResultSet(ResultSet rs) throws SQLException {
        Video video = null;
        while (rs.next()) {
            video = new Video();
            video.setVideoId(rs.getLong("video_id"));
            video.setUserId(rs.getLong("user_id"));
//            video.setContent(rs.getBytes("content"));
            video.setTitle(rs.getString("title"));
        }
        return video;
    }

    @Override
    public List<Video> searchVideosByTitle(String title) {
        return null;
    }

    @Override
    public boolean insertVideo(Video video) {
        String insertTableSQL = "INSERT INTO video"
                + "(title, content, user_id) VALUES"
                + "(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, video.getTitle());
            preparedStatement.setBytes(2, video.getContent());
            preparedStatement.setLong(3, video.getUserId());
            preparedStatement.executeUpdate();
            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            tableKeys.next();
            long videoId = tableKeys.getLong(1);
            video.setVideoId(videoId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteVideo(Video video) {
        return false;
    }
}
