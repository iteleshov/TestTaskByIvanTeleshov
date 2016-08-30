package com.teleshovivan;

import com.teleshovivan.model.User;

import java.time.LocalDate;

/**
 * Created by Jager on 27.08.2016.
 */
public class TestData {

    public static final int USER1_ID = 1;
    public static final int USER2_ID = 2;
    public static final int USER3_ID = 3;
    public static final int USER4_ID = 4;
    public static final int NOT_EXIST_USER_ID = 666;

    public static final User USER1 = new User(USER1_ID, "Razin", "Stepan", "Timofeevich", "Back-end developer", LocalDate.of(1974, 6, 5));
    public static final User USER2 = new User(USER2_ID, "Pugachev", "Emelyan", "Ivanovich", "Front-end developer", LocalDate.of(1975, 01, 10));
    public static final User USER3 = new User(USER3_ID, "Romanov", "Peter", "Alekseevich", "Team lead", LocalDate.of(1972, 5, 30));
    public static final User USER4 = new User(USER4_ID, "Romanova", "Ekaterina", "Alekseevna", "QA engineer", LocalDate.of(1962, 6, 28));

    public static final User NEW_USER = new User("Romanov", "Mikhail", "Fyodorovich", "DevOps", LocalDate.of(1996, 7, 22));

    public static final String LAST_NAME_SEARCH = "Romanov";
    public static final String FIRST_NAME_SEARCH = "Emelyan";
    public static final String MIDDLE_NAME_SEARCH = "Timofeevich";
    public static final LocalDate START_BIRTHDAY_SEARCH = LocalDate.of(1970, 1, 1);
    public static final LocalDate END_BIRTHDAY_SEARCH = LocalDate.of(1975, 1, 1);
}
