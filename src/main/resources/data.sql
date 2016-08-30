DELETE FROM users;
ALTER SEQUENCE seq RESTART WITH 1;

INSERT INTO users (last_name, first_name, middle_name, appointment, birthday) VALUES
    ('Razin', 'Stepan', 'Timofeevich', 'Back-end developer', '1974-06-05'),
    ('Pugachev', 'Emelyan', 'Ivanovich' , 'Front-end developer', '1975-01-10'),
    ('Romanov', 'Peter', 'Alekseevich', 'Team lead', '1972-05-30'),
    ('Romanova', 'Ekaterina', 'Alekseevna', 'QA engineer', '1962-06-28');
