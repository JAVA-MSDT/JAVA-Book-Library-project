CREATE TYPE role AS ENUM(
'ADMIN',
'LIBRARIAN',
'READER'
);

CREATE TABLE IF NOT EXISTS user_table
(
    user_id              BIGSERIAL       NOT NULL PRIMARY KEY,
    first_name           VARCHAR(15)  NOT NULL,
    last_name            VARCHAR(15)  NOT NULL,
    user_email           VARCHAR(255) NOT NULL UNIQUE,
    user_login           VARCHAR(20)  NOT NULL UNIQUE,
    user_password        VARCHAR(255) NOT NULL,
    user_role            role[],
    user_blocked         BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS book_table
(
    book_id        BIGSERIAL      NOT NULL PRIMARY KEY ,
    book_title     VARCHAR(40) NOT NULL,
    quantity       INT DEFAULT 1
);

CREATE TYPE place AS ENUM(
'HALL',
'HOME'
);

CREATE TABLE IF NOT EXISTS book_order_table
(
    order_id             BIGSERIAL   NOT NULL PRIMARY KEY,
    user_id              BIGINT   NOT NULL,
    book_id              BIGINT   NOT NULL,
    order_date           DATE NOT NULL,
    returning_date       DATE NOT NULL,
    reading_place        place[],
    book_returned        BOOLEAN DEFAULT false,
    FOREIGN KEY (book_id) REFERENCES book_table (book_id),
    FOREIGN KEY (user_id) REFERENCES user_table (user_id)
);
