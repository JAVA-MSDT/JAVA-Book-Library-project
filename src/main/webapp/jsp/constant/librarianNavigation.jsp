<%@ page import="com.epam.library.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

        <link rel="stylesheet" href="../../css/navBar.css">
        <link rel="stylesheet" href="../../css/readrHeader.css">

                <nav class="topnav">
            <button id="mobile" onclick="showMenu()">Menu</button>
    
            <ul id="navList">
            <li><a href="controller?command=librarian-profile">Librarian Profile</a></li>
            <li><a href="controller?command=librarian-book-store">Book Store</a></li>
            <li><a href="controller?command=librarian-order-store">Orders List</a></li>
            <li><a href="controller?command=librarian-display-readers">Readers List</a></li>
            <li class="log-out-btn" style="float: right; background-color: gray"><a href="controller?command=logout">Logout</a></li>
            <li style="float: right" class = "dropdown">
                <a class="dropbtn">Language</a>
                <div class="dropdown-content">
                    <a href="#">EN</a>
                    <a href="#">RU</a>
                    <a href="#">AR</a>
                    </div>
                </li>
            </ul>
        </nav>
        <header class="infoHeader">
            <div class="avContainer">
                <img class="avatar" src="../../img/profImage.jpg">
                <div class="overlay">
                    <a href="#"><h3>Edit</h3></a>
                </div>
            </div>

            <h2>${requestScope.reader.name}</h2> <h2> ${requestScope.reader.lastName}</h2>
        </header>