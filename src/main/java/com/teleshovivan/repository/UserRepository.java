package com.teleshovivan.repository;

import com.teleshovivan.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jager on 25.08.2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
}
