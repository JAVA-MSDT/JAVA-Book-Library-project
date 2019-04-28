<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="locale"/>
<fmt:setLocale value="${param.language}"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../../css/mainNavigation.css">
<li style="float: right" class = "dropdown">
    <a class="dropbtn"> <fmt:message key="nav.language"/> </a>
    <div class="dropdown-content">
        <a href="?sessionLocal=ar"> <fmt:message key="nav.language.ar"/> </a>
        <a href="?sessionLocal=ru"> <fmt:message key="nav.language.ru"/> </a>
        <a href="?sessionLocal=en"> <fmt:message key="nav.language.en"/> </a>
    </div>
</li>