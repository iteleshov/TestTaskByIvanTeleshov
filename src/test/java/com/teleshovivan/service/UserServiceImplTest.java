package com.teleshovivan.service;

import com.teleshovivan.Application;
import com.teleshovivan.model.User;
import com.teleshovivan.util.exceptions.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;

import static com.teleshovivan.TestData.*;

/**
 * Created by Jager on 26.08.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Sql(scripts = {"classpath:database/schema.sql", "classpath:database/data.sql"}, config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceImplTest {

    @Autowired
    protected UserService service;

    @Test
    public void testSave() throws Exception {
        User created = service.save(NEW_USER);
        NEW_USER.setId(created.getId());
        User [] expected = {USER1, USER2, USER3, USER4, NEW_USER};
        Assert.assertArrayEquals(expected, service.getAll().toArray());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER1_ID);
        User [] expected = {USER2, USER3, USER4};
        Assert.assertArrayEquals(expected, service.getAll().toArray());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(NOT_EXIST_USER_ID);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(1);
        Assert.assertEquals(USER1, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(NOT_EXIST_USER_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        User updatedUser = new User(USER1);
        updatedUser.setAppointment("new appointment");
        updatedUser.setLastName("new last name");
        updatedUser.setFirstName("new first name");
        updatedUser.setMiddleName("new middle name");
        updatedUser.setBirthday(LocalDate.of(2012, 12, 12));
        service.update(updatedUser);
        Assert.assertEquals(updatedUser, service.get(USER1_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        User [] expected = {USER1, USER2, USER3, USER4};
        Assert.assertArrayEquals(expected, service.getAll().toArray());
    }

    @Test
    public void testGetAllByLastName() throws Exception {
        User [] expected = {USER3};
        Assert.assertArrayEquals(expected, service.getAllByLastName(LAST_NAME_SEARCH).toArray());
    }

    @Test
    public void testGetAllByFirstName() throws Exception {
        User [] expected = {USER2};
        Assert.assertArrayEquals(expected, service.getAllByFirstName(FIRST_NAME_SEARCH).toArray());
    }

    @Test
    public void testGetAllByMiddleName() throws Exception {
        User [] expected = {USER1};
        Assert.assertArrayEquals(expected, service.getAllByMiddleName(MIDDLE_NAME_SEARCH).toArray());
    }


    @Test
    public void testGetAllByBirthday() throws Exception {
        User [] expected = {USER1, USER3};
        Assert.assertArrayEquals(expected, service.getAllByBirthdayBetween(START_BIRTHDAY_SEARCH, END_BIRTHDAY_SEARCH).toArray());
    }
}