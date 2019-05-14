<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>
        <c:if test="${ not empty sessionScope.user}">
            ${sessionScope.user.name}
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <fmt:message key="label.title.epam"/>
        </c:if>
    </title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/accountBodyStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookCard.css">
</head>
<body>
<%-- Special page for a non registered users or for regesterd users--%>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/navigation.jsp"/>

<div class="profileContainer" style="width: 90%">
    <div class="basicInfo">
        <h1><fmt:message key="label.book.store"/></h1>
        <hr>
        <%-- Adding - sorting - seraching bar --%>

        <div class="main-row">
            <div class="search-column" style="width: 50%">
                <form name="search-book" action="controller" method="post">
                    <input type="hidden" name="command" value="search-book">
                    <input class="search-field" type="text" name="query" required
                           placeholder="<fmt:message key="button.search"/> "/>
                    <select class="select-option" name="type">
                        <option value="name"><fmt:message key="label.book.name"/></option>
                    </select>
                    <input class="submit-button" type="submit" value="<fmt:message key="button.search"/>"/>
                </form>
            </div>
            <div class="sort-column" style="width: 50%">
                <form name="sort-book" action="controller" method="post">
                    <input type="hidden" name="command" value="sort-book">
                    <div class="row-option">
                        <div class="label-col">
                            <label for="sort-option"> <fmt:message key="label.sort.by"/> </label>
                        </div>
                        <div class="option-col">
                            <select id="sort-option" class="select-option" name="type">
                                <option value="name"><fmt:message key="label.name"/></option>
                            </select>
                            <input class="submit-button" type="submit" value="<fmt:message key="button.sort"/>"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <%-- in case of search for a book and it is not exist the below message will be displayed
    instead of book list --%>
        <c:choose>
            <c:when test="${not empty requestScope.bookNotExist}">
                <h2 class="permission" style="color: brown"><fmt:message key="message.book.not.exist"/></h2> <br>
            </c:when>
            <c:otherwise>
                <%--Book Cards --%>
                <div class="container">
                    <c:forEach var="bookList" items="${requestScope.bookList}" varStatus="loop">
                        <div class="book-card">
                            <div class="card-container">
                                <img src="../../img/effective.jpg" alt="Avatar" class="book-image">
                                <div class="name-overlay">
                                    <div class="book-info">
                                        <div class="book-name">
                                            <h3>${bookList.name}</h3>
                                        </div>
                                        <hr>
                                        <div class="book-row">
                                            <div class="book-available"><c:if test="${bookList.quantity > 0}">
                                                <p><fmt:message key="label.available"/></p>
                                            </c:if>
                                                <c:if test="${bookList.quantity == 0}">
                                                    <p><fmt:message key="label.not.available"/></p>
                                                </c:if>
                                            </div>
                                            <c:if test="${sessionScope.user.role eq 'LIBRARIAN' or sessionScope.user.role eq 'ADMIN'}">
                                                <div class="book-quantity">
                                                    <p> ${bookList.quantity}</p>
                                                </div>
                                            </c:if>

                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="book-row">
                                <div class="view-book">
                                    <form name="view-book" action="controller" method="post">
                                        <input type="hidden" name="command" value="view-book">
                                        <input class="book-btn" type="submit" name="view"
                                               value="<fmt:message key="button.view"/> "/>
                                        <input type="hidden" name="id" value="<c:out value="${bookList.id}"/>"/>
                                    </form>
                                </div>
                                <c:if test="${sessionScope.user.role eq 'LIBRARIAN' or sessionScope.user.role eq 'ADMIN'}">

                                    <div class="edit-book">
                                        <form name="administration-edit-book" action="controller" method="post">
                                            <input type="hidden" name="command" value="administration-edit-book">
                                            <input class="book-btn" type="submit" name="view"
                                                   value="<fmt:message key="button.edit"/> "/>
                                            <input type="hidden" name="id" value="<c:out value="${bookList.id}"/>"/>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp"/>
</body>

</html>
