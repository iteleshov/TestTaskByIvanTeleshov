package com.teleshovivan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.teleshovivan.Application;
import com.teleshovivan.model.User;
import com.teleshovivan.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import static com.teleshovivan.TestData.*;




/**
 * Created by Jager on 28.08.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Sql(scripts = {"classpath:database/schema.sql", "classpath:database/data.sql"}, config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserRestControllerTest {

    public static final String REST_URL = UserRestController.REST_URL + "/";

    @Autowired
    private UserService service;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectWriter objectWriter;

    @PostConstruct
    public void postConstruct() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get(REST_URL + USER1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.lastName", is(USER1.getLastName())))
                .andExpect(jsonPath("$.firstName", is(USER1.getFirstName())))
                .andExpect(jsonPath("$.middleName", is(USER1.getMiddleName())))
                .andExpect(jsonPath("$.appointment", is(USER1.getAppointment())))
                .andExpect(jsonPath("$.birthday", is(USER1.getBirthday().toString())));
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + NOT_EXIST_USER_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreate() throws Exception {
       String jsonNewUser = objectWriter.writeValueAsString(NEW_USER);

       mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonNewUser))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        User updaedUser = new User(USER1);
        updaedUser.setFirstName("new name");
        updaedUser.setLastName("new last name");
        updaedUser.setMiddleName("new middle name");
        updaedUser.setBirthday(LocalDate.of(2012, 12, 12));

        String jsonUpdatedUser = objectWriter.writeValueAsString(updaedUser);

        mockMvc.perform(put(REST_URL + updaedUser.getId())
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonUpdatedUser))
                .andExpect(status().isOk());

        Assert.assertEquals(updaedUser, service.get(updaedUser.getId()));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER1_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + NOT_EXIST_USER_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllByLastName() throws Exception {
        mockMvc.perform(get(REST_URL + "filterByLastName?lastName=" + USER3.getLastName()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].lastName", is(USER3.getLastName())))
                .andExpect(jsonPath("$.[0].firstName", is(USER3.getFirstName())))
                .andExpect(jsonPath("$.[0].middleName", is(USER3.getMiddleName())))
                .andExpect(jsonPath("$.[0].appointment", is(USER3.getAppointment())))
                .andExpect(jsonPath("$.[0].birthday", is(USER3.getBirthday().toString())));
    }

    @Test
    public void testGetAllByFirstName() throws Exception {
        mockMvc.perform(get(REST_URL + "filterByFirstName")
                .param("firstName", USER2.getFirstName()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].lastName", is(USER2.getLastName())))
                .andExpect(jsonPath("$.[0].firstName", is(USER2.getFirstName())))
                .andExpect(jsonPath("$.[0].middleName", is(USER2.getMiddleName())))
                .andExpect(jsonPath("$.[0].appointment", is(USER2.getAppointment())))
                .andExpect(jsonPath("$.[0].birthday", is(USER2.getBirthday().toString())));
    }

    @Test
    public void testGetAllByMiddleName() throws Exception {
        mockMvc.perform(get(REST_URL + "filterByMiddleName")
                .param("middleName", USER1.getMiddleName()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].lastName", is(USER1.getLastName())))
                .andExpect(jsonPath("$.[0].firstName", is(USER1.getFirstName())))
                .andExpect(jsonPath("$.[0].middleName", is(USER1.getMiddleName())))
                .andExpect(jsonPath("$.[0].appointment", is(USER1.getAppointment())))
                .andExpect(jsonPath("$.[0].birthday", is(USER1.getBirthday().toString())));
    }

    @Test
    public void testGetAllByAppointment() throws Exception {
        mockMvc.perform(get(REST_URL + "filterByAppointment")
                .param("appointment", USER3.getAppointment()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].lastName", is(USER3.getLastName())))
                .andExpect(jsonPath("$.[0].firstName", is(USER3.getFirstName())))
                .andExpect(jsonPath("$.[0].middleName", is(USER3.getMiddleName())))
                .andExpect(jsonPath("$.[0].appointment", is(USER3.getAppointment())))
                .andExpect(jsonPath("$.[0].birthday", is(USER3.getBirthday().toString())));
    }


    @Test
    public void testGetAllByBirthdayBetween() throws Exception {
        User [] expected = {USER1, USER3};

        mockMvc.perform(get(REST_URL + "between")
                .param("startDate", START_BIRTHDAY_SEARCH.toString())
                .param("endDate", END_BIRTHDAY_SEARCH.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].lastName", is(expected[0].getLastName())))
                .andExpect(jsonPath("$.[0].firstName", is(expected[0].getFirstName())))
                .andExpect(jsonPath("$.[0].middleName", is(expected[0].getMiddleName())))
                .andExpect(jsonPath("$.[0].appointment", is(expected[0].getAppointment())))
                .andExpect(jsonPath("$.[0].birthday", is(expected[0].getBirthday().toString())))
                .andExpect(jsonPath("$.[1].lastName", is(expected[1].getLastName())))
                .andExpect(jsonPath("$.[1].firstName", is(expected[1].getFirstName())))
                .andExpect(jsonPath("$.[1].middleName", is(expected[1].getMiddleName())))
                .andExpect(jsonPath("$.[1].appointment", is(expected[1].getAppointment())))
                .andExpect(jsonPath("$.[1].birthday", is(expected[1].getBirthday().toString())));
    }

    @Test
    public void testGetAllByBirthdayBetweenWithoutStartDateAndEndDate() throws Exception {
        mockMvc.perform(get(REST_URL + "between"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)));
    }

}