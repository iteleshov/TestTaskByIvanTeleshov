package com.teleshovivan.repository;

import com.teleshovivan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jager on 25.08.2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    List<User> findAll();
}
