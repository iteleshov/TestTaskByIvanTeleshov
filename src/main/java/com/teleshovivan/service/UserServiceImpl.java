package com.teleshovivan.service;

import com.teleshovivan.model.User;
import com.teleshovivan.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.teleshovivan.repository.UserRepository;

import java.util.List;

/**
 * Created by nz on 26.08.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public User get(int id) {
        User user = repository.findOne(id);
        if(user == null){
            throw new NotFoundException("Not found user with id=" + id);
        }
        return user;
    }

    @Override
    public void update(User user) {
        User updatedUser = repository.save(user);
        if(updatedUser == null) {
            throw new NotFoundException("Not found user with id=" + user.getId());
        }
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
