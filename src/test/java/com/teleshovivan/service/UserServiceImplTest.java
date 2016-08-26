package com.teleshovivan.service;

import com.teleshovivan.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.teleshovivan.repository.UserRepository;

import java.time.LocalDate;

/**
 * Created by nz on 26.08.16.
 */
public class UserServiceImplTest {

    @Autowired
    protected UserService service;
    @Autowired
    protected UserRepository repository;

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {
        System.out.println(service);
        System.out.println(repository);
        User user = service.get(1);
        System.out.println(user);
        Assert.assertEquals(new User("Teleshov", "Ivan", "Aleksandrovich", "Java Developer", LocalDate.parse("1991-08-09")), user);
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

}