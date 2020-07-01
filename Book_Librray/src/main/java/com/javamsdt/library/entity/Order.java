package com.javamsdt.library.entity;

import java.sql.Date;
import java.util.Objects;

import com.javamsdt.library.entity.enumeration.ReadingPlace;
import com.javamsdt.library.util.validate.ArgumentValidator;

public class Order {

    private long orderId;
    private long bookId;
    private long userId;
    private Date orderDate;
    private Date returningDate;
    private ReadingPlace readingPlace;
    private boolean bookReturned;


    public Order(long userId, Date orderDate, Date returningDate, ReadingPlace readingPlace) {
        setUserId(userId);
        setOrderDate(orderDate);
        setReturningDate(returningDate);
        setReadingPlace(readingPlace);
    }

    public Order(long bookId, long userId, Date orderDate, Date returningDate, ReadingPlace readingPlace) {
        setBookId(bookId);
        setUserId(userId);
        setOrderDate(orderDate);
        setReturningDate(returningDate);
        setReadingPlace(readingPlace);
    }

    public Order(long orderId, long bookId, long userId, Date orderDate, Date returningDate, ReadingPlace readingPlace) {
        setOrderId(orderId);
        setBookId(bookId);
        setUserId(userId);
        setOrderDate(orderDate);
        setReturningDate(returningDate);
        setReadingPlace(readingPlace);
    }

    public Order(long orderId, long bookId, long userId, Date orderDate, Date returningDate, ReadingPlace readingPlace, boolean bookReturned) {
        setOrderId(orderId);
        setBookId(bookId);
        setUserId(userId);
        setOrderDate(orderDate);
        setReturningDate(returningDate);
        setReadingPlace(readingPlace);
        setBookReturned(bookReturned);
    }

    public Order() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        ArgumentValidator.checkForNull(orderId, "Order orderId not allow to be null in Order class");
        this.orderId = orderId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        ArgumentValidator.checkForNull(bookId, "Order bookId not allow to be null in Order class");
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        ArgumentValidator.checkForNull(userId, "Order userId not allow to be null in Order class");
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        ArgumentValidator.checkForNull(orderDate, "Order orderDate not allow to be null in Order class");
        this.orderDate = orderDate;
    }

    public Date getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(Date returningDate) {
        ArgumentValidator.checkForNull(returningDate, "Order returningDate not allow to be null in Order class");
        this.returningDate = returningDate;
    }

    public ReadingPlace getReadingPlace() {
        return readingPlace;
    }

    public void setReadingPlace(ReadingPlace readingPlace) {
        ArgumentValidator.checkForNull(readingPlace, "Order readingPlace not allow to be null in Order class");
        this.readingPlace = readingPlace;
    }

    public boolean isBookReturned() {
        return bookReturned;
    }

    public void setBookReturned(boolean bookReturned) {
        this.bookReturned = bookReturned;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        Order order = (Order) o;
        return bookReturned == order.bookReturned &&
                orderId == order.orderId &&
                bookId == order.bookId &&
                userId == order.userId &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(returningDate, order.returningDate) &&
                readingPlace == order.readingPlace;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (orderId ^ (orderId >>> 32));
        result = prime * result + (int) (bookId ^ (bookId >>> 32));
        result = prime * result + (int) (userId ^ (userId >>> 32));
        result = prime * result + ((orderDate != null) ? orderDate.hashCode() : 0);
        result = prime * result + ((returningDate != null) ? returningDate.hashCode() : 0);
        result = prime * result + ((readingPlace != null) ? readingPlace.hashCode() : 0);
        result = prime * result + (bookReturned ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", returningDate=" + returningDate +
                ", readingPlace=" + readingPlace +
                ", bookReturned=" + bookReturned +
                '}';
    }
}
