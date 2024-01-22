package com.javamsdt.library.model.dto.orderservice;

import java.sql.Date;
import java.util.Objects;

import com.javamsdt.library.entity.enumeration.ReadingPlace;

public class AdministrationOrderDisplay {

    private long id;
    private String bookName;
    private String userName;
    private String userEmail;
    private Date orderDate;
    private Date returningDate;
    private ReadingPlace readingPlace;
    private boolean bookReturned;

    public AdministrationOrderDisplay(long id, String bookName, String userName, String userEmail, Date orderDate, Date returningDate, ReadingPlace readingPlace, boolean bookReturned) {
        this.id = id;
        this.bookName = bookName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.returningDate = returningDate;
        this.readingPlace = readingPlace;
        this.bookReturned = bookReturned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
        AdministrationOrderDisplay orderDisplay = (AdministrationOrderDisplay) o;
        return id == orderDisplay.id &&
                bookReturned == orderDisplay.bookReturned &&
                Objects.equals(bookName, orderDisplay.bookName) &&
                Objects.equals(userName, orderDisplay.userName) &&
                Objects.equals(userEmail, orderDisplay.userEmail) &&
                Objects.equals(orderDate, orderDisplay.orderDate) &&
                Objects.equals(returningDate, orderDisplay.returningDate) &&
                readingPlace == orderDisplay.readingPlace;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((bookName != null) ? bookName.hashCode() : 0);
        result = prime * result + ((userName != null) ? userName.hashCode() : 0);
        result = prime * result + ((userEmail != null) ? userEmail.hashCode() : 0);
        result = prime * result + ((orderDate != null) ? orderDate.hashCode() : 0);
        result = prime * result + ((returningDate != null) ? returningDate.hashCode() : 0);
        result = prime * result + ((readingPlace != null) ? readingPlace.hashCode() : 0);
        result = prime * result + ((bookReturned) ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return "AdministrationOrderDisplay{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", orderDate=" + orderDate +
                ", returningDate=" + returningDate +
                ", readingPlace=" + readingPlace +
                ", bookReturned=" + bookReturned +
                '}';
    }
}
