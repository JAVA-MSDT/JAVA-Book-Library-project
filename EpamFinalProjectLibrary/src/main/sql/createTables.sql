CREATE TABLE user_table
(
    id        BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(15)  NOT NULL,
    last_name VARCHAR(15)  NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    login     VARCHAR(20)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    role      ENUM ("ADMIN", "LIBRARIAN", "READER") DEFAULT "READER",
    blocked   BOOLEAN                               DEFAULT false
);

CREATE TABLE book
(
    id       BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(40) NOT NULL,
    quantity INT DEFAULT 1
);

CREATE TABLE order_book
(
    id             BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id        BIGINT   NOT NULL,
    book_id        BIGINT   NOT NULL,
    order_date     DATETIME NOT NULL,
    returning_date DATETIME NOT NULL,
    reading_place  ENUM ("HALL", "HOME") DEFAULT "HOME",
    book_returned  BOOLEAN               DEFAULT false,
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (user_id) REFERENCES user_table (id)
)