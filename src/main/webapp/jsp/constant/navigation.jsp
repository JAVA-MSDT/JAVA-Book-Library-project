<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="locale"/>
<fmt:setLocale value="${param.language}"/>
<html lang="${param.language}">
    <head>
        <title>Epam Library</title>
        <meta charset="utf-8">
        <meta name="description" content="Epam Navigation">
        <meta name="author" content="Ahmed Samy">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../../css/mainNavigation.css">
    </head>
    <body>
        <nav class="top-nav">
            <button id="mobile" onclick="showMenu()">Menu</button>
    
            <ul id="nav-list">
            <li><a href="../../index.jsp"> <fmt:message key="nav.home"/> </a></li>
                <li><a href="controller?command=display-book"> <fmt:message key="label.book.store"/> </a></li>
                <jsp:include page="commonNavigationBtn.jsp"/>
            <li style="float: right; background-color: darkslategrey "><a href="../../login.jsp"> <fmt:message key="label.login"/> </a></li>
                <jsp:include page="language.jsp"/>
            </ul>
        </nav>
       
        <script>
        
        </script>
    </body>
</html>