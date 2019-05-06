<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<link rel="stylesheet" href="../../css/navigation.css">
<link rel="stylesheet" href="../../css/accountHeader.css"/>
<nav class="top-nav">
    <button id="mobile" onclick="">Menu</button>

    <ul id="nav-list">
        <li><a href="controller?command=user-profile"><fmt:message key="nav.profile"/></a></li>
        <li><a href="controller?command=display-book"><fmt:message key="label.book.store"/></a></li>
        <li class="dropdown">
            <a class="dropbtn"> <fmt:message key="button.order"/> </a>
            <div class="dropdown-content">
                <a href="controller?command=user-order"><fmt:message key="label.order.list"/></a>
                <a href="#"> <fmt:message key="button.order.history"/> </a>
            </div>
        </li>
        <jsp:include page="/jsp/commoncode/commonNavigationBtn.jsp"/>
        <li class="log-out-btn" style="float: right; background-color: darkslategrey"><a
                href="controller?command=logout"><fmt:message key="nav.logout"/></a></li>
        <jsp:include page="/jsp/commoncode/language.jsp"/>
    </ul>
</nav>
<header class="infoHeader">
    <div class="avContainer">
        <img class="avatar" src="../../img/profImage.jpg">
        <div class="overlay">
            <form name="edit-profile-image" action="controller" method="post">
                <input type="hidden" name="command" value="edit-profile-image">
                <input class="add-button" type="submit" name="edit" value="<fmt:message key="button.edit"/>"/>
            </form>
        </div>
    </div>

    <h2>${sessionScope.user.name}</h2>
    <h2> ${sessionScope.user.lastName}</h2>
</header>
<script>

</script>