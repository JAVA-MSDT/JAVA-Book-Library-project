package com.epam.library.model.dao.query;

public class OrderQuery {
    public final static String INSERT_ORDER = "INSERT INTO order_book(book_id, user_id, order_date, returning_date, " +
            "reading_place) VALUES(?, ?, ?, ?, ?)";

    public final static String UPDATE_ORDER_DATA = "UPDATE order_book SET book_id = ? user_id = ? order_date = ?" +
            "returning_date = ? reading_place = ?, book_returned = ? WHERE id = ?";
    public final static String UPDATE_ORDER_BOOK_ID = "UPDATE order_book SET book_id = ? WHERE id = ?";
    public final static String UPDATE_ORDER_USER_ID = "UPDATE order_book SET user_id = ? WHERE id = ?";
    public final static String UPDATE_ORDER_ORDER_DATE = "UPDATE order_book SET order_date = ? WHERE id = ?";
    public final static String UPDATE_ORDER_RETURNING_DATE= "UPDATE order_book SET returning_date = ? WHERE id = ?";
    public final static String UPDATE_ORDER_READING_PLACE = "UPDATE order_book SET reading_place = ? WHERE id = ?";
    public final static String UPDATE_ORDER_BOOK_RETURNED = "UPDATE order_book SET book_returned = ? WHERE id = ?";

    public final static String SELECT_ALL_ORDERS = "SELECT * FROM order_book";
    public final static String SELECT_ORDER_BY_ID = "SELECT * FROM order_book WHERE id = ?";
    public final static String SELECT_ORDER_BY_BOOK_ID = "SELECT * FROM order_book WHERE book_id = ?";
    public final static String SELECT_ORDER_BY_USER_ID = "SELECT * FROM order_book WHERE user_id = ?";
    public final static String SELECT_ORDER_BY_ORDER_DATE = "SELECT * FROM order_book WHERE order_date = ?";
    public final static String SELECT_ORDER_BY_RETURNING_DATE = "SELECT * FROM order_book WHERE returning_date = ?";
    public final static String SELECT_ORDER_BY_READING_PALCE = "SELECT * FROM order_book WHERE reading_place = ?";

    public final static String DELETE_ORDER = "DELETE FROM order_book WHERE id = ?";
    public final static String DELET_ORDER_BY_USER_ID = "DELETE FROM order_book WHERE user_id = ?";
}
