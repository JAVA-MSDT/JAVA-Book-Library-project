package com.epam.library.util.constant.query;

public class BookQuery {
    public final static String INSERT_BOOK = "INSERT INTO book(name, quantity) VALUES (?, ?)";

    public final static String UPDATE_BOOK = "UPDATE book SET name = ?, quantity = ? WHERE id = ?";
    public final static String UPDATE_BOOK_QUANTITY = "UPDATE book SET quantity= ? WHERE id= ?";
    public final static String UPDATE_BOOK_NAME = "UPDATE book SET name= ? WHERE id= ?";

    public final static String SELECT_ALL_BOOKS = "SELECT * FROM book";
    public final static String SELECT_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    public final static String SELECT_BOOK_BY_NAME = "SELECT * FROM book WHERE name = ?";
    public final static String SELECT_BOOK_BY_QUANTITY = "SELECT * FROM book WHERE quantity = ?";

    public final static String SELECT_ALL_BOOKS_SORTED_BY_NAME = "SELECT * FROM book ORDER BY name ASC";
    public final static String SELECT_ALL_BOOKS_SORTED_BY_QUANTITY = "SELECT * FROM book ORDER BY quantity ASC";

    public final static String DELETE_BOOK = "DELETE FROM book WHERE id= ?";
}
