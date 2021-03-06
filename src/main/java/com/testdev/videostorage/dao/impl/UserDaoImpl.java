package com.testdev.videostorage.dao.impl;

import com.testdev.videostorage.dao.UserDao;
import com.testdev.videostorage.domain.Gender;
import com.testdev.videostorage.domain.User;

import java.sql.*;
import java.util.List;

/**
 * @author oleh.krupenia.
 */
public class UserDaoImpl implements UserDao {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        String sql = "SELECT user_id, first_name, last_name, email, birthday, gender, oauth, password FROM users where user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            user = populateUserFromResultSet(rs);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        String sql = "SELECT user_id, first_name, last_name, email, birthday, gender, oauth, password FROM users where email = ? and password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            user = populateUserFromResultSet(rs);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User populateUserFromResultSet(ResultSet rs) throws SQLException {
        User user = null;
        while (rs.next()) {
            user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setGender(Gender.valueOf(rs.getString("gender")));
//            user.setBirthday(rs.getDate("birthday").toLocalDate());
            user.setPassword(rs.getString("password"));
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
