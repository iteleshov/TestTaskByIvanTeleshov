package com.teleshovivan;

import com.teleshovivan.model.User;

import java.time.LocalDate;

/**
 * Created by Jager on 27.08.2016.
 */
public class TestData {

    public static final User USER1 = new User(1, "Razin", "Stepan", "Timofeevich", "Back-end developer", LocalDate.parse("1674-06-05"));
    public static final User USER2 = new User(2, "Pugachev", "Emelyan", "Ivanovich", "Front-end developer", LocalDate.parse("1775-01-10"));
    public static final User USER3 = new User(3, "Romanov", "Peter", "Alekseevich", "Team lead", LocalDate.parse("1672-05-30"));
    public static final User USER4 = new User(4, "Romanova", "Ekaterina", "Alekseevna", "QA engineer", LocalDate.parse("1762-06-28"));

    public static final User NEW_USER = new User("Romanov", "Mikhail", "Fyodorovich", "DevOps", LocalDate.parse("1596-07-22"));

    public static final int NOT_EXIST_USER_ID = 666;
}
