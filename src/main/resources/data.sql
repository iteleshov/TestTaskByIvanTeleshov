DELETE FROM users;
ALTER SEQUENCE seq RESTART WITH 1;

INSERT INTO users (last_name, first_name, middle_name, appointment, birthday) VALUES
    ('Teleshov', 'Ivan', 'Aleksandrovich', 'Java Developer', '1991-08-09'),
    ('Osnitskaya', 'Natalia', 'asdfasdf', 'HR manager', '2015-08-25'),
    ('Kurashov', 'Alexey', 'Fedorovich', 'Java Developer', '2015-08-25'),
    ('Simachev', 'Denis', 'Aleksandrovich', 'Java Developer', '2015-08-25');