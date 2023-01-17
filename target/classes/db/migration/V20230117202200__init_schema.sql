CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE ${flyway:defaultSchema}.genre
(
    id serial NOT NULL,
    name varchar(100) UNIQUE NOT NULL,
    CONSTRAINT pk_genre_id PRIMARY KEY (id)
);

CREATE TABLE ${flyway:defaultSchema}.producer
(
    id serial NOT NULL,
    fullname varchar(100) UNIQUE NOT NULL,
    CONSTRAINT pk_producer_id PRIMARY KEY (id)
);

CREATE TABLE ${flyway:defaultSchema}.user
(
    id serial NOT NULL,
    firstname varchar(100) UNIQUE NOT NULL,
    lastname varchar(100) UNIQUE NOT NULL,
    CONSTRAINT pk_user_id PRIMARY KEY (id)
);

CREATE TABLE ${flyway:defaultSchema}.person
(
    id serial NOT NULL,
    firstname varchar(100) UNIQUE NOT NULL,
    lastname varchar(100) UNIQUE NOT NULL,
    CONSTRAINT pk_person_id PRIMARY KEY (id)
);

CREATE TABLE ${flyway:defaultSchema}.position
(
    id serial NOT NULL,
    name varchar(100) UNIQUE NOT NULL,
    CONSTRAINT pk_position_id PRIMARY KEY (id)
);

CREATE TABLE ${flyway:defaultSchema}.staff
(
    id serial NOT NULL,
    firstname varchar(100) UNIQUE NOT NULL,
    lastname varchar(100) UNIQUE NOT NULL,
    position_id int4 NOT NULL,
    CONSTRAINT pk_staff_id PRIMARY KEY (id),
    CONSTRAINT fk_position_id FOREIGN KEY (position_id) REFERENCES ${flyway:defaultSchema}.position (id)
);

CREATE TYPE action AS ENUM ('GIVE_OUT', 'PICK_UP');

CREATE TABLE ${flyway:defaultSchema}.person_list
(
    id serial NOT NULL,
    action_type action NOT NULL,
    staff_id int4 NOT NULL,
    user_id int4 NOT NULL,
    CONSTRAINT pk_person_list_id PRIMARY KEY (id),
    CONSTRAINT fk_staff_id FOREIGN KEY (staff_id) REFERENCES ${flyway:defaultSchema}.staff (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES ${flyway:defaultSchema}.user (id)
);

CREATE TABLE ${flyway:defaultSchema}.disc
(
    id serial NOT NULL,
    name varchar(100) NOT NULL,
    country varchar(100) NULL,
    genre_id int4 NOT NULL,
    year int4 NULL,
    producer_disc int4 NOT NULL,
    disc_id  int4 NOT NULL,
    CONSTRAINT pk_disc_id PRIMARY KEY (id),
    CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) REFERENCES ${flyway:defaultSchema}.genre (id),
    CONSTRAINT fk_producer_disc FOREIGN KEY (producer_disc) REFERENCES ${flyway:defaultSchema}.producer (id),
    CONSTRAINT fk_disc_id FOREIGN KEY (disc_id) REFERENCES ${flyway:defaultSchema}.person_list (id)
);



