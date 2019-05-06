package com.epam.library.entity;


import com.epam.library.util.validate.ArgumentValidator;

import java.util.Objects;

public class Book {

    private long id;
    private String name;
    private int quantity;

    public Book() {
    }

    public Book(String name, int quantity) {
        setName(name);
        setQuantity(quantity);
    }

    public Book(long id, String name, int quantity) {
        setId(id);
        setName(name);
        setQuantity(quantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        ArgumentValidator.checkForNull(id, "Book Id not allow to be null in Book class");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ArgumentValidator.checkForNull(name, "Book name not allow to be null in Book class");
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        ArgumentValidator.checkForNegativity(quantity, "Book quantity not allow to be negative in Book class");
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        Book book = (Book) o;
        return quantity == book.quantity &&
                id == book.id &&
                Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name != null) ? name.hashCode() : 0);
        result = prime * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
