package com.javamsdt.library.util.constant.query;

public class BookQuery {
    public final static String INSERT_BOOK = "INSERT INTO book_table(book_title, quantity) VALUES (?, ?::BIGINT)";

    public final static String UPDATE_BOOK = "UPDATE book_table SET book_title = ?, quantity = ?::BIGINT WHERE book_id = ?::BIGINT";
    public final static String UPDATE_BOOK_QUANTITY = "UPDATE book_table SET quantity = ?::BIGINT WHERE book_id = ?::BIGINT";
    public final static String UPDATE_BOOK_TITLE = "UPDATE book_table SET book_title= ? WHERE book_id = ?::BIGINT";

    public final static String SELECT_ALL_BOOKS = "SELECT * FROM book_table";
    public final static String SELECT_BOOK_BY_ID = "SELECT * FROM book_table WHERE book_id = CAST( ? AS BIGINT)";
    public final static String SELECT_BOOK_BY_TITLE = "SELECT * FROM book_table WHERE book_title = ?";
    public final static String SELECT_BOOK_BY_QUANTITY = "SELECT * FROM book_table WHERE quantity = ?::BIGINT";

    public final static String SELECT_ALL_BOOKS_SORTED_BY_TITLE = "SELECT * FROM book_table ORDER BY book_title ASC";
    public final static String SELECT_ALL_BOOKS_SORTED_BY_QUANTITY = "SELECT * FROM book_table ORDER BY quantity ASC";

    public final static String DELETE_BOOK = "DELETE FROM book_table WHERE book_id = ?::BIGINT";
}
