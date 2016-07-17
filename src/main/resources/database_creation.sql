CREATE DATABASE storage;
CREATE TABLE users (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(50),
  birthday DATE,
  gender VARCHAR(10),
  oauth VARCHAR(250),
  password VARCHAR(50)
);

CREATE TABLE videos (

);