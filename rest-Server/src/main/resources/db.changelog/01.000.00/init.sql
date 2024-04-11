DO $$
BEGIN

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'role_enum') THEN
        CREATE TYPE role_enum AS ENUM ('USER', 'ADMIN');
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'age_category_enum') THEN
       CREATE TYPE age_category_enum AS ENUM ('ZERO', 'SIX', 'TWELVE', 'SIXTEEN', 'EIGHTEEN');
    END IF;

END $$;


CREATE TABLE IF NOT EXISTS personalities (
    email varchar(100) primary key,
    password varchar(100),
    role role_enum default 'USER'
);

CREATE TABLE IF NOT EXISTS films (
    id BIGSERIAL primary key,
    name varchar(100) unique,
    year integer,
    producer varchar(50),
    duration varchar(50),
    actors text,
    trailer text,
    info varchar(1000),
    current boolean,
    image varchar(500),
    production_country varchar(100),
    age_category age_category_enum
);

CREATE TABLE IF NOT EXISTS cinemas (
    id BIGSERIAL primary key,
    name varchar(100),
    latitude double precision,
    longitude double precision,
    info varchar(1000),
    image varchar(500),
    number_phone BIGINT
);

CREATE TABLE IF NOT EXISTS admins (
    id BIGSERIAL primary key,
    email varchar(100),
    password varchar(100),
    cinema_id BIGINT,
    FOREIGN KEY (cinema_id) REFERENCES cinemas(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS halls (
    id BIGSERIAL primary key,
    name varchar(100),
    rows integer,
    columns integer,
    cinema_id BIGINT,

    FOREIGN KEY (cinema_id) REFERENCES cinemas(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sessions (
    id BIGSERIAL primary key,
    date TIMESTAMP default CURRENT_TIMESTAMP,
    price integer,
    cinema_id BIGINT,
    film_id BIGINT,
    hall_id BIGINT,

    FOREIGN KEY (cinema_id) REFERENCES cinemas(id) ON DELETE CASCADE,
    FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
    FOREIGN KEY (hall_id) REFERENCES halls(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL primary key,
    email varchar(100),
    password varchar(100),
    name varchar(100),
    number_phone BIGINT
);


CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL primary key,
    token varchar(100),
    session_id BIGINT,
    user_id BIGINT,

    FOREIGN KEY (session_id) REFERENCES sessions(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS tickets (
    id BIGSERIAL primary key,
    section integer,
    subsection integer,
    order_id BIGINT,
    session_id BIGINT,

    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (session_id) REFERENCES sessions(id)
);

CREATE TABLE IF NOT EXISTS cinemas_films (
    cinema_id BIGINT,
    film_id BIGINT,

    PRIMARY KEY (cinema_id, film_id),
    FOREIGN KEY (cinema_id) REFERENCES cinemas(id) ON DELETE CASCADE,
    FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE
);
