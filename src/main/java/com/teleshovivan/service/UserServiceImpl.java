package com.teleshovivan.service;

import com.teleshovivan.model.User;
import com.teleshovivan.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.teleshovivan.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Jager on 26.08.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public User get(int id) throws NotFoundException{
        User user = repository.findOne(id);
        if(user == null){
            throw new NotFoundException("Not found user with id = " + id);
        }
        return user;
    }

    @Override
    public void update(User user) throws NotFoundException{
        User updatedUser = repository.save(user);
        if(updatedUser == null) {
            throw new NotFoundException("Not found user with id = " + user.getId());
        }
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public List<User> getAllByLastName(String lastName) {
        return repository.findAllByLastName(lastName);
    }

    @Override
    public List<User> getAllByFirstName(String firstName) {
        return repository.findAllByFirstName(firstName);
    }

    @Override
    public List<User> getAllByMiddleName(String middleName) {
        return repository.findAllByMiddleName(middleName);
    }

    @Override
    public List<User> getAllByBirthdayBetween(LocalDate startDate, LocalDate endDate) {
        startDate = startDate == null ? startDate = LocalDate.of(1, 1, 1) : startDate;
        endDate = endDate == null ? endDate = LocalDate.of(3000, 1, 1) : endDate;
        return repository.findAllByBirthdayBetween(startDate, endDate);
    }

    @Override
    public List<User> getAllByAppointment(String appointment) {
        return repository.findAllByAppointment(appointment);
    }
}
