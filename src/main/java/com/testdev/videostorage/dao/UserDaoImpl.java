package com.testdev.videostorage.dao;

import com.testdev.videostorage.domain.Gender;
import com.testdev.videostorage.domain.User;
import com.testdev.videostorage.utils.ConnectionManager;

import java.sql.*;
import java.util.List;

/**
 * @author oleh.krupenia.
 */
public class UserDaoImpl implements UserDao {
    private Connection connection = ConnectionManager.getConnection();

    @Override
    public User getUserById(Long id) {
        User user = null;
        String sql = "SELECT user_id, first_name, last_name, email, birthday, gender, oauth, password FROM Employees where user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, user.getUserId());
            ResultSet rs = preparedStatement.executeQuery(sql);
            while (rs.next()) {
                user.setUserId(rs.getLong("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setGender(Gender.valueOf(rs.getString("gender")));
                user.setBirthday(rs.getDate("birthday").toLocalDate());
                user.setPassword(rs.getString("password"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List getUsers() {
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        String insertTableSQL = "INSERT INTO users"
                + "(first_name, last_name, email, birthday, gender, oauth, password) VALUES"
                + "(?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, user.getBirthday() != null ? Date.valueOf(user.getBirthday()) : null);
            preparedStatement.setString(5, Gender.UNKNOWN.name());
            preparedStatement.setString(6, user.getoAuth());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            tableKeys.next();
            long userId = tableKeys.getLong(1);
            user.setUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
