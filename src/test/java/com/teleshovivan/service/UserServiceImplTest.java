package com.teleshovivan.service;

import com.teleshovivan.Application;
import com.teleshovivan.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.teleshovivan.repository.UserRepository;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

/**
 * Created by nz on 26.08.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceImplTest {

    @Autowired
    protected UserService service;

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {
        System.out.println(service);
        User expectedUser = new User("Teleshov", "Ivan", "Aleksandrovich", "Java Developer", LocalDate.parse("1991-08-09"));
        expectedUser.setId(1);
        User user = service.get(1);
        System.out.println(user);
        Assert.assertEquals(expectedUser, user);
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

}