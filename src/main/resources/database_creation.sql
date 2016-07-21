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
  video_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(150),
  content LONGBLOB,
  user_id BIGINT NOT NULL,
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (user_id)
);