<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<link rel="stylesheet" href="../../css/navigation.css">
<nav class="top-nav">
    <button id="mobile" onclick="showMenu()">Menu</button>

    <ul id="nav-list">
        <li><a href="../../index.jsp"> <fmt:message key="nav.home"/> </a></li>
        <li><a href="controller?command=display-book"> <fmt:message key="label.book.store"/> </a></li>
        <jsp:include page="commonNavigationBtn.jsp"/>
        <li style="float: right; background-color: darkslategrey "><a href="../../login.jsp"> <fmt:message
                key="label.login"/> </a></li>
        <jsp:include page="language.jsp"/>
    </ul>
</nav>
