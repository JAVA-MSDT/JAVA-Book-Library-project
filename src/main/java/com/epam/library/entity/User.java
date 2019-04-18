package com.epam.library.entity;

import com.epam.library.entity.enumeration.Role;
import com.epam.library.util.validate.ArgumentValidator;

import java.util.Objects;

public class User {

    private long id;
    private String name;
    private String LastName;
    private String email;
    private String login;
    private String password;
    private Role role;
    private boolean blocked;


    public User() {
    }

    public User(String name, String LastName, String email, String login, String password, Role role) {
        setName(name);
        setLastName(LastName);
        setEmail(email);
        setLogin(login);
        setPassword(password);
        setRole(role);
    }

    public User(String name, String LastName, String email, String login, String password) {
        setName(name);
        setLastName(LastName);
        setEmail(email);
        setLogin(login);
        setPassword(password);
    }

    public User(long id, String name, String lastName, String email, String login, String password, Role role, boolean blocked) {
        setId(id);
        setName(name);
        setLastName(lastName);
        setEmail(email);
        setLogin(login);
        setPassword(password);
        setRole(role);
        setBlocked(blocked);

    }

    public User(long id, String name, String lastName, String email, String login, String password, boolean blocked) {
        setId(id);
        setName(name);
        setLastName(lastName);
        setEmail(email);
        setLogin(login);
        setPassword(password);
        setBlocked(blocked);

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        ArgumentValidator.checkForNull(id, "User id not allow to be null in User class");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ArgumentValidator.checkForNull(name, "User name not allow to be null in User class");
        this.name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        ArgumentValidator.checkForNull(lastName, "User Last name not allow to be null in User class");
        this.LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        ArgumentValidator.checkForNull(email, "User email not allow to be null in User class");
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        ArgumentValidator.checkForNull(login, "User Login not allow to be null in User class");
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        ArgumentValidator.checkForNull(password, "User Password not allow to be null in User class");
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        ArgumentValidator.checkForNull(role, "User Role not allow to be null in User class");
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (this == o){
            return true;
        }
        User user = (User) o;
        return  id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(LastName, user.LastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                blocked == user.blocked;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name != null) ? name.hashCode() :0);
        result = prime * result + ((LastName != null) ? LastName.hashCode() : 0);
        result = prime * result + ((email != null) ? email.hashCode() :0);
        result = prime * result + ((login != null) ? login.hashCode() : 0);
        result = prime * result + ((password != null) ? password.hashCode() :0);
        result = prime * result + ((role != null) ? role.hashCode() : 0);
        result = prime * result + (blocked ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }
}
