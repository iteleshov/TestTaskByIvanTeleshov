package com.teleshovivan.controller;

import com.teleshovivan.model.User;
import com.teleshovivan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
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

    @RequestMapping(value = "/filterByFirstName", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getByFirstName(@RequestParam(value = "firstName") String firstName) {
        List<User> users = service.getAllByFirstName(firstName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @RequestMapping(value = "/filterByLastName", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getByLastName(@RequestParam(value = "lastName") String lastName) {
        List<User> users = service.getAllByLastName(lastName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/filterByMiddleName", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getByMiddleName(@RequestParam(value = "middleName") String lastName) {
        List<User> users = service.getAllByMiddleName(lastName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/filterByAppointment", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getByAppointment(@RequestParam(value = "appointment") String appointment) {
        List<User> users = service.getAllByAppointment(appointment);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/between", method = RequestMethod.GET)
    public List<User> getBetween(
            @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return service.getAllByBirthDayBetween(startDate, endDate);
    }
}
