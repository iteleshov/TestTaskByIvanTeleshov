DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq;

CREATE SEQUENCE seq;

CREATE TABLE users
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('seq'),
  last_name   VARCHAR NOT NULL,
  first_name  VARCHAR NOT NULL,
  middle_name VARCHAR,
  position    VARCHAR NOT NULL,
  birthday    DATE
);
