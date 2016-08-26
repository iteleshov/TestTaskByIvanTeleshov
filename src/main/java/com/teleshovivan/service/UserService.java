package com.teleshovivan.service;

import com.teleshovivan.model.User;

import java.util.List;

/**
 * Created by nz on 26.08.16.
 */
public interface UserService {
    User save(User user);

    void delete(int id);

    User get(int id);

    void update(User user);

    List<User> getAll();
}
