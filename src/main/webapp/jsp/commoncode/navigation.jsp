<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/libraryNavigation.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/siteHeader.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/libraryHeader.css"/>

<nav class="top-nav" id="i-top-nav">
    <ul id="nav-list" class="nav-list">
        <li><a href="../../index.jsp" class="active"> <fmt:message key="nav.home"/> </a></li>
        <c:if test="${sessionScope.user == null}">
            <li><a href="controller?command=display-book"> <fmt:message key="label.book.store"/> </a></li>
        </c:if>

        <c:choose>
            <c:when test="${sessionScope.user.role eq 'READER'}">
                <li><a href="controller?command=profile" class="active"><fmt:message key="nav.profile"/></a></li>
                <li><a href="controller?command=display-book"><fmt:message key="label.book.store"/></a></li>
                <li class="dropdown">
                    <a class="dropbtn"> <fmt:message key="button.order"/> </a>
                    <div class="dropdown-content">
                        <a href="controller?command=user-order"><fmt:message key="label.order.list"/></a>
                        <a href="#"> <fmt:message key="button.order.history"/> </a>
                    </div>
                </li>
            </c:when>

            <c:when test="${sessionScope.user.role eq 'LIBRARIAN' or sessionScope.user.role eq 'ADMIN'}">
                <li><a href="controller?command=profile" class="active"> ${sessionScope.user.role} <fmt:message key="nav.profile"/></a></li>
                <li><a href="controller?command=administration-book-store"><fmt:message key="label.book.store"/></a></li>
                <li><a href="controller?command=administration-order-list"><fmt:message key="label.order.list"/></a></li>
                <li><a href="controller?command=administration-display-user"><fmt:message key="label.reader.list"/></a></li>
            </c:when>
        </c:choose>

        <li><a href="#"><fmt:message key="nav.news"/></a></li>
        <li><a href="#"><fmt:message key="nav.contact"/></a></li>
        <li><a href="#"><fmt:message key="nav.about"/></a></li>

        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <li style="float: right; background-color: darkslategrey ">
                    <a href="../../login.jsp"> <fmt:message key="label.login"/> </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="log-out-btn" style="float: right; background-color: darkslategrey">
                    <a href="controller?command=logout"><fmt:message key="nav.logout"/></a>
                </li>
            </c:otherwise>
        </c:choose>

        <li style="float: right" class="dropdown">
            <a class="dropbtn"> <fmt:message key="nav.language"/> </a>
            <div class="dropdown-content">
                <form method="POST" action="controller">
                    <input type="hidden" name="command" value="change-language"/>
                    <button type="submit" name="language"
                            value="en"><fmt:message key="nav.language.en"/>
                    </button>
                    <button type="submit" name="language"
                            value="de"><fmt:message key="nav.language.de"/>
                    </button>
                    <button type="submit" name="language"
                            value="ar"><fmt:message key="nav.language.ar"/>
                    </button>
                    <button type="submit" name="language"
                            value="ru"><fmt:message key="nav.language.ru"/>
                    </button>
                </form>
            </div>
        </li>
        <li class="icon">
            <a href="javascript:void(0);" style="font-size:15px;" class="active" onclick="myFunction()">&#9776;</a>
        </li>
    </ul>

</nav>

<c:choose>
    <c:when test="${sessionScope.user == null}">
        <header>
            <img src="../../img/book.jpg" class="headerImage" alt="header image">
        </header>
    </c:when>
    <c:otherwise>
        <header class="infoHeader">
            <div class="avContainer">
                <img class="avatar" src="../../img/profImage.jpg">
                <div class="overlay">
                        <a href="controller?command=administration-edit-user">
                            <fmt:message key="button.edit"/>
                        </a>
                </div>
            </div>

            <h2>${sessionScope.user.name}</h2>
            <h2> ${sessionScope.user.lastName}</h2>
        </header>
    </c:otherwise>
</c:choose>
<c:if test="${sessionScope.user != null}">

</c:if>

<script>
    function myFunction() {
        var x = document.getElementById("i-top-nav");
        if(x.className === "top-nav"){
            x.className += " responsive";
        }else {
            x.className = "top-nav";
        }
    }
</script>