package com.epam.library.model.dto.orderservice.user;

import com.epam.library.entity.enumeration.ReadingPlace;

import java.sql.Date;
import java.util.Objects;

public class UserOrderDisplay {

    private long id;
    private long userId;
    private String bookName;
    private Date orderDate;
    private Date returningDate;
    private ReadingPlace readingPlace;
    private boolean bookReturned;

    public UserOrderDisplay(long id, long userId, String bookName, Date orderDate, Date returningDate,
                            ReadingPlace readingPlace, boolean bookReturned) {
        this.id = id;
        this.userId = userId;
        this.bookName = bookName;
        this.orderDate = orderDate;
        this.returningDate = returningDate;
        this.readingPlace = readingPlace;
        this.bookReturned = bookReturned;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(Date returningDate) {
        this.returningDate = returningDate;
    }

    public ReadingPlace getReadingPlace() {
        return readingPlace;
    }

    public void setReadingPlace(ReadingPlace readingPlace) {
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
        UserOrderDisplay orderDisplay = (UserOrderDisplay) o;
        return id == orderDisplay.id &&
                userId == orderDisplay.userId &&
                bookReturned == orderDisplay.bookReturned &&
                Objects.equals(bookName, orderDisplay.bookName) &&
                Objects.equals(orderDate, orderDisplay.orderDate) &&
                Objects.equals(returningDate, orderDisplay.returningDate) &&
                readingPlace == orderDisplay.readingPlace;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (userId ^ (userId >>> 32));
        result = prime * result + ((bookName != null) ? bookName.hashCode() : 0);
        result = prime * result + ((orderDate != null) ? orderDate.hashCode() : 0);
        result = prime * result + ((returningDate != null) ? returningDate.hashCode() : 0);
        result = prime * result + ((readingPlace != null) ? readingPlace.hashCode() : 0);
        result = prime * result + ((bookReturned) ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return "UserOrderDisplay{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookName='" + bookName + '\'' +
                ", orderDate=" + orderDate +
                ", returningDate=" + returningDate +
                ", readingPlace=" + readingPlace +
                ", bookReturned=" + bookReturned +
                '}';
    }
}
