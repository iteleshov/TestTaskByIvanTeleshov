package com.teleshovivan.repository;

import com.teleshovivan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Jager on 25.08.2016.
 */
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByLastName(String lastName);

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByMiddleName(String middleName);

    List<User> findAllByBirthdayBetween(LocalDate startDate, LocalDate endDate);

    List<User> findAllByAppointment(String appointment);


}
