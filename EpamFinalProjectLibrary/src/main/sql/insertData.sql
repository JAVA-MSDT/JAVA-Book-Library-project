INSERT INTO user_table(id, name, last_name, email, login, password, role, blocked)
VALUES('1', 'Ahmed', 'Samy', 'serenitydiver@hotmail.com', 'JAVAMSDT', MD5('java'), 'ADMIN',false);

INSERT INTO user_table(name, last_name, email, login, password, role, blocked)
VALUES('Alaa', 'Gamal', 'alaa@mail.com', 'alaa', MD5('gamal'), 'LIBRARIAN',0);

INSERT INTO user_table(name, last_name, email, login, password, role, blocked)
VALUES('readerOne', 'readOne', 'first@read.com', 'first', MD5('1111'), 'READER', 0);

INSERT INTO user_table(name, last_name, email, login, password, role, blocked)
VALUES('readerTwo', 'readTwo', 'second@read.com', 'second', MD5('2222'), 'READER', 0);

INSERT INTO user_table(name, last_name, email, login, password, role, blocked)
VALUES('readerThree', 'readThree', 'third@read.com', 'third', MD5('3333'), 'READER', 0);

INSERT INTO user_table(name, last_name, email, login, password, role, blocked)
VALUES('readerFour', 'readFour', 'four@read.com', 'four', MD5('4444'), 'READER', 0);


INSERT INTO book(name, quantity)
VALUES('Java SE', '10');

INSERT INTO book(name, quantity)
VALUES('Servlet', '12');

INSERT INTO book(name, quantity)
VALUES('Java Web', '5');

INSERT INTO book(name, quantity)
VALUES('Html', '14');

INSERT INTO book(name, quantity)
VALUES('Computer Science', '20');

INSERT INTO book(name, quantity)
VALUES('CS50', '8');

INSERT INTO order_book(id, book_id, user_id, order_date, returning_date, reading_place)
VALUES ('1', '2', '3', '2008-10-29', '2008-11-29', 'HALL');

INSERT INTO order_book(book_id, user_id, order_date, returning_date, reading_place)
VALUES ('4', '2', '2009-11-20 17:56:59', '2009-12-29 15:56:59', 'HOME');

INSERT INTO order_book(book_id, user_id, order_date, returning_date, reading_place)
VALUES ('1', '5', '2010-01-11 14:56:59', '2010-01-20 15:56:59', 'HALL');

INSERT INTO order_book(book_id, user_id, order_date, returning_date, reading_place)
VALUES ('3', '3', '2015-04-13 17:56:59', '2015-05-20 15:56:59', 'HOME');

/*SELECT order_book.id, user_table.name, user_table.email, book.name, order_book.order_date,
order_book.returning_date, order_book.reading_place, order_book.book_returned FROM order_book
INNER JOIN user_table ON order_book.user_id = user_table.id
INNER JOIN book ON order_book.book_id = book.id;
*/