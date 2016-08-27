package com.teleshovivan.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Jager on 25.08.2016.
 */

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(User user)
    {
        this(user.id, user.getLastName(), user.getFirstName(), user.getMiddleName(), user.getAppointment(), user.getBirthDay());
    }

    public User(String lastName, String firstName, String middleName, String appointment, LocalDate birthDay) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.appointment = appointment;
        this.birthday = birthDay;
    }

    public User(Integer id, String lastName, String firstName, String middleName, String appointment, LocalDate birthDay) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.appointment = appointment;
        this.birthday = birthDay;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @NotEmpty
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "appointment")
    private String appointment;

    @Column(name = "birthday")
    private LocalDate birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public LocalDate getBirthDay() {
        return birthday;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", appointment='" + appointment + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (middleName != null ? !middleName.equals(user.middleName) : user.middleName != null) return false;
        if (appointment != null ? !appointment.equals(user.appointment) : user.appointment != null) return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (appointment != null ? appointment.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
