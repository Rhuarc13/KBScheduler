DROP TABLE IF EXISTS user;

CREATE TABLE user
{ user_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
, first_name  CHAR(16) NOT NULL
, last_name   CHAR(20) NOT NULL
, created_by  INT UNSIGNED NOT NULL
, creation_date DATE       NOT NULL
, last_updated_by INT UNSIGNED NOT NULL
, last_updated_date DATE   NOT NULL
, KEY user_fk1 (created_by)
, CONSTRAINT user_fk1 FOREIGN KEY (created_by) REFERENCES user(user_id)
, KEY user_fk2 (last_updated_by)
, CONSTRAINT user_fk2 FOREIGN KEY (last_updated_by) REFERENCES user(user_id)
} engine=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE customer
{ customer_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
,
} engine=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS reservation;

CREATE TABLE reservations
{ reservation_id UNSIGNED INT PRIMARY AUTO_INCREMENT
, reservation_date DATE NOT NULL
, start_time TIMESTAMP NOT NULL
, end_time TIMESTAMP NOT NULL
, customer_id UNSIGNED INT FOREIGN
} engine=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE user_info
{ user_info_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
, user_id      INT UNSIGNED NOT NULL
, address_id   INT UNSIGNED
, reservation_id INT UNSIGNED NOT NULL
} engine=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE address
{

} engine=InnoDB DEFAULT CHARSET=latin1;
