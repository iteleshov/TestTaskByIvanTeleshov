package com.teleshovivan.service;

import com.teleshovivan.model.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by nz on 26.08.16.
 */
public interface UserService {
    User save(User user);

    void delete(int id);

    User get(int id);

    void update(User user);

    List<User> getAll();

    List<User> getAllByLastName(String lastName);

    List<User> getAllByFirstName(String firstName);

    List<User> getAllByMiddleName(String middleName);

    List<User> getAllByBirthDayBetween(LocalDate startDate, LocalDate endDate);

    List<User> getAllByAppointment(String appointment);


}
