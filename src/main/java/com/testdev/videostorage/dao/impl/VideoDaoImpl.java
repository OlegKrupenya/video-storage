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
    private final Connection connection;

    public VideoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Video> getVideosForUser(Long userId) {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT * FROM videos where user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Video video = populateVideoFromResultSet(rs);
                if (video != null) {
                    videos.add(video);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }

    @Override
    public Video getVideoById(Long videoId) {
        Video video = null;
        String sql = "SELECT * FROM videos where video_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, videoId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                video = populateVideoFromResultSet(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video;
    }

    private Video populateVideoFromResultSet(ResultSet rs) throws SQLException {
        Video video = new Video();
        video.setVideoId(rs.getLong("video_id"));
        video.setUserId(rs.getLong("user_id"));
        video.setContent(rs.getBytes("content"));
        video.setTitle(rs.getString("title"));
        return video;
    }

    @Override
    public List<Video> searchVideosByTitle(String title) {
        return null;
    }

    @Override
    public boolean insertVideo(Video video) {
        String insertTableSQL = "INSERT INTO videos"
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
        String deleteTableSQL = "DELETE FROM videos WHERE video_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteTableSQL)) {
            preparedStatement.setLong(1, video.getVideoId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
