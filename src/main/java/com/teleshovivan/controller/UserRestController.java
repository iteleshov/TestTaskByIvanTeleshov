package com.teleshovivan.controller;

import com.teleshovivan.model.User;
import com.teleshovivan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.teleshovivan.controller.UserRestController.REST_URL;

/**
 * Created by Jager on 28.08.2016.
 */
@RestController
@RequestMapping(REST_URL)
public class UserRestController {

    public static final String REST_URL = "/rest";
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        List<User> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable int id) {
        User user = service.get(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        user.setId(null);
        User createdUser = service.save(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        service.update(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
