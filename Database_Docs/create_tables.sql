TEE create_tables.txt

DROP TABLE IF EXISTS user;

CREATE TABLE user
{ user_id           INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
, first_name        CHAR(16)     NOT NULL
, last_name         CHAR(20)     NOT NULL
, created_by        INT UNSIGNED NOT NULL
, creation_date     DATE         NOT NULL
, last_updated_by   INT UNSIGNED NOT NULL
, last_updated_date DATE         NOT NULL
, KEY user_fk1 (created_by)
, CONSTRAINT user_fk1 FOREIGN KEY (created_by)      REFERENCES user(user_id)
, KEY user_fk2 (last_updated_by)
, CONSTRAINT user_fk2 FOREIGN KEY (last_updated_by) REFERENCES user(user_id)
} engine=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE customer
{ customer_id       INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
, first_name        CHAR(16)     NOT NULL
, last_name         CHAR(20)     NOT NULL
, reservation_id    INT UNSIGNED NOT NULL
, address_id        INT UNSIGNED NOT NULL
, phone             INT UNSIGNED NOT NULL
, created_by        INT UNSIGNED NOT NULL
, creation_date     DATE         NOT NULL
, last_updated_by   INT UNSIGNED NOT NULL
, last_updated_date DATE         NOT NULL
, KEY customer_fk1 (reservation_id)
, CONSTRAINT customer_fk1 FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
, KEY customer_fk2 (address_id)
, CONSTRAINT customer_fk2 FOREIGN KEY (address_id) REFERENCES address(address_id)
, KEY customer_fk3 (created_by)
, CONSTRAINT customer_fk3 FOREIGN KEY (created_by) REFERENCES user(user_id)
, KEY customer_fk4 (last_updated_by)
, CONSTRAINT customer_fk4 FOREIGN KEY (last_updated_by) REFERENCES user(user_id)
} engine=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS reservation;

CREATE TABLE reservations
{ reservation_id    INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
, reservation_date  DATE         NOT NULL
, start_time        TIMESTAMP    NOT NULL
, end_time          TIMESTAMP    NOT NULL
, customer_id       INT UNSIGNED NOT NULL
, address_id        INT UNSIGNED NOT NULL
, created_by        INT UNSIGNED NOT NULL
, creation_date     DATE         NOT NULL
, last_updated_by   INT UNSIGNED NOT NULL
, last_updated_date DATE         NOT NULL
, KEY reservations_fk1 (customer_id)
, CONSTRAINT reservations_fk1 FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
, KEY reservations_fk2 (address_id)
, CONSTRAINT reservations_fk2 FOREIGN KEY (address_id)  REFERENCES address(address_id)
, KEY reservations_fk3 (created_by)
, CONSTRAINT reservations_fk3 FOREIGN KEY (created_by) REFERENCES user(user_id)
, KEY reservations_fk4 (last_updated_by)
, CONSTRAINT reservations_fk4 FOREIGN KEY (last_updated_by) REFERENCES user(user_id)
} engine=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE address
{ address_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT
, street     CHAR(60) NOT NULL
, city       CHAR(20) NOT NULL
, state      CHAR(20) NOT NULL
, KEY user_fk1 (created_by)
, CONSTRAINT user_fk1 FOREIGN KEY (created_by) REFERENCES user(user_id)
, KEY user_fk2 (last_updated_by)
, CONSTRAINT user_fk2 FOREIGN KEY (last_updated_by) REFERENCES user(user_id)
} engine=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO user 
( first_name
, last_name
, created_by
, creation_date
, last_updated_by
, last_updated_date   
) VALUES 
( "SYSTEM"
, "ADMIN"
, 1
, UTC_DATE()
, 1
, UTC_DATE()   
);

INSERT INTO user 
( first_name
, last_name
, created_by
, creation_date
, last_updated_by
, last_updated_date   
) VALUES 
( "Shem"
, "Sedrick"
, 1
, UTC_DATE()
, 1
, UTC_DATE()   
);

INSERT INTO user 
( first_name
, last_name
, created_by
, creation_date
, last_updated_by
, last_updated_date   
) VALUES 
( "Jared"
, "Mefford"
, 1
, UTC_DATE()
, 1
, UTC_DATE()   
);

INSERT INTO user 
( first_name
, last_name
, created_by
, creation_date
, last_updated_by
, last_updated_date   
) VALUES 
( "Weston"
, "Clark"
, 1
, UTC_DATE()
, 1
, UTC_DATE()   
);

INSERT INTO user 
( first_name
, last_name
, created_by
, creation_date
, last_updated_by
, last_updated_date   
) VALUES 
( "DBConnection"
, "Java"
, 1
, UTC_DATE()
, 1
, UTC_DATE()   
);

NOTEE
