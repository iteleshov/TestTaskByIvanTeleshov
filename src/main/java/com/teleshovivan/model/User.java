package com.teleshovivan.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Jager on 25.08.2016.
 */
@NamedQueries({
@NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User u"),
@NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id")
})
@Entity
public class User {

    public static final String GET_ALL = "User.getAll";
    public static final String DELETE = "User.delete";

    public User()
    {

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String lastName, String firstName, String middleName, String position) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.position = position;
    }

    public User(String lastName, String firstName, String middleName, LocalDate birthDay) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDay = birthDay;
    }

    public User(String lastName, String firstName, String middleName, String position, LocalDate birthDay) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.position = position;
        this.birthDay = birthDay;
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

    @Column(name = "position")
    private String position;

    @Column
    private LocalDate birthDay;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
