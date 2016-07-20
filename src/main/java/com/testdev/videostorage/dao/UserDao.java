package com.testdev.videostorage.dao;

import com.testdev.videostorage.domain.User;

import java.util.List;

/**
 * @author oleh.krupenia.
 */
public interface UserDao {
    User getUserById(Long id);
    User getUserByEmailAndPassword(String email, String password);
    List getUsers();
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
