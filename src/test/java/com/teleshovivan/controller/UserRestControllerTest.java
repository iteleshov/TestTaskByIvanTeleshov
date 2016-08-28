package com.teleshovivan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.teleshovivan.Application;
import com.teleshovivan.model.User;
import com.teleshovivan.service.UserService;
import com.teleshovivan.util.exceptions.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static com.teleshovivan.TestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import static com.teleshovivan.controller.UserRestController.REST_URL;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

/**
 * Created by Jager on 28.08.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserRestControllerTest {

    @Autowired
    private UserService service;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectWriter objectWriter;

    @Before
    public void setup() {
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
        updaedUser.setBirthday(LocalDate.parse("2012-12-12"));

        String jsonUpdatedUser = objectWriter.writeValueAsString(updaedUser);

        mockMvc.perform(put("/" +updaedUser.getId())
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

}