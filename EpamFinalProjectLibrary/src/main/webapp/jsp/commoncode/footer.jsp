<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/siteFooter.css">

<footer class="footer">
    <div class="footLinks">
        <div class="footMenu">
            <ul>
                <li><a href="../../index.jsp">Home</a></li>
                <li><a href="../../login.jsp">Reader Area</a></li>
                <li><a href="#">News</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">About</a></li>
            </ul>
        </div>
        <div class="socialMedia">
            <ul>
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">Google Plus</a></li>
                <li><a href="#">Linkedin</a></li>
            </ul>

        </div>
    </div>
    <div class="copyright">
        <p>&#64; Copyright: Epam System Belarus 2019</p>
    </div>
</footer>

