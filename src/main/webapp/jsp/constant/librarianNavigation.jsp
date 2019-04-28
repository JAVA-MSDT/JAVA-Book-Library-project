<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
        <link rel="stylesheet" href="../../css/mainNavigation.css">
        <link rel="stylesheet" href="../../css/accountHeader.css">

                <nav class="top-nav">
            <button id="mobile" onclick="showMenu()">Menu</button>
    
            <ul id="nav-list">
            <li><a href="controller?command=librarian-profile"><fmt:message key="nav.librarian"/></a></li>
            <li><a href="controller?command=librarian-book-store"><fmt:message key="label.book.store"/></a></li>
            <li><a href="controller?command=librarian-order-list"><fmt:message key="label.order.list"/></a></li>
            <li><a href="controller?command=librarian-display-readers"><fmt:message key="label.reader.list"/></a></li>
                <jsp:include page="/jsp/constant/commonNavigationBtn.jsp"/>
            <li class="log-out-btn" style="float: right; background-color: darkslategrey"><a href="controller?command=logout"><fmt:message key="nav.logout"/></a></li>
                <jsp:include page="/jsp/constant/language.jsp"/>
            </ul>
        </nav>
        <header class="infoHeader">
            <div class="avContainer">
                <img class="avatar" src="../../img/profImage.jpg">
                <div class="overlay">
                    <form name="librarian-add-reader" action="controller" method="post">
                        <input type="hidden" name="command" value="librarian-add-reader">
                        <input class="add-button" type="submit" name="edit" value="<fmt:message key="button.edit"/>"/>
                    </form>
                    <%--<a href="#"><h3><fmt:message key="button.edit"/></h3></a>--%>
                </div>
            </div>

            <h2>${sessionScope.reader.name}</h2> <h2> ${sessionScope.reader.lastName}</h2>
        </header>