package com.javamsdt.library.util.constant.query;

public class OrderQuery {
    public final static String INSERT_ORDER = "INSERT INTO book_order_table(book_id, user_id, order_date, returning_date, " +
            "reading_place) VALUES(?::BIGINT, ?::BIGINT, ?::date, ?::date, ARRAY[?]::place[])";

    public final static String UPDATE_ORDER_DATA = "UPDATE book_order_table SET book_id = ?::BIGINT, user_id = ?::BIGINT, order_date = ?::date," +
            "returning_date = ?::date, reading_place = ARRAY[?]::place[], book_returned = ?::boolean WHERE order_id = ?::BIGINT";
    public final static String UPDATE_ORDER_BOOK_ID = "UPDATE book_order_table SET book_id = ?::BIGINT WHERE order_id = ?::BIGINT";
    public final static String UPDATE_ORDER_USER_ID = "UPDATE book_order_table SET user_id = ?::BIGINT WHERE order_id = ?::BIGINT";
    public final static String UPDATE_ORDER_ORDER_DATE = "UPDATE book_order_table SET order_date = ?::date WHERE order_id = ?::BIGINT";
    public final static String UPDATE_ORDER_RETURNING_DATE = "UPDATE book_order_table SET returning_date = ?::date WHERE order_id = ?::BIGINT";
    public final static String UPDATE_ORDER_READING_PLACE = "UPDATE book_order_table SET reading_place = ARRAY[?]::place[] WHERE order_id = ?::BIGINT";
    public final static String UPDATE_ORDER_BOOK_RETURNED = "UPDATE book_order_table SET book_returned = ?::boolean WHERE order_id = ?::BIGINT";

    public final static String SELECT_ALL_ORDERS = "SELECT * FROM book_order_table";
    public final static String SELECT_ORDER_BY_ID = "SELECT * FROM book_order_table WHERE order_id = ?::BIGINT";
    public final static String SELECT_ORDER_BY_BOOK_ID = "SELECT * FROM book_order_table WHERE book_id = ?::BIGINT";
    public final static String SELECT_ORDER_BY_USER_ID = "SELECT * FROM book_order_table WHERE user_id = ?::BIGINT";
    public final static String SELECT_ORDER_BY_ORDER_DATE = "SELECT * FROM book_order_table WHERE order_date = ?::date";
    public final static String SELECT_ORDER_BY_RETURNING_DATE = "SELECT * FROM book_order_table WHERE returning_date = ?::date";
    public final static String SELECT_ORDER_BY_READING_PLACE = "SELECT * FROM book_order_table WHERE reading_place = ARRAY[?]::place[]";

    public final static String DELETE_ORDER = "DELETE FROM book_order_table WHERE order_id = ?::BIGINT";
    public final static String DELETE_ORDER_BY_USER_ID = "DELETE FROM book_order_table WHERE user_id = ?::BIGINT";

    public final static String SELECT_ORDER_FOR_REVIEW = "SELECT book_order_table.order_id, user_table.first_name, user_table.user_email, book_table.book_title, book_order_table.order_date, " +
            "book_order_table.returning_date, book_order_table.reading_place, book_order_table.book_returned FROM book_order_table " +
            "INNER JOIN user_table ON book_order_table.user_id = user_table.user_id " +
            "INNER JOIN book_table ON book_order_table.book_id = book_table.book_id";

    public final static String SELECT_ORDER_FOR_USER = "SELECT book_order_table.order_id, book_order_table.user_id, book_table.book_title, book_order_table.order_date, " +
            "book_order_table.returning_date, book_order_table.reading_place, book_order_table.book_returned FROM book_order_table " +
            "INNER JOIN book_table ON book_order_table.book_id = book_table.book_id";
}
