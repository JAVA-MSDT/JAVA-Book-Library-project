package com.epam.library.model.service.orderservice;

import com.epam.library.entity.enumeration.ReadingPlace;

import java.sql.Date;

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
