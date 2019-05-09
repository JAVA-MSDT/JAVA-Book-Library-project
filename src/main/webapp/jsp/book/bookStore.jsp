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
    <link rel="stylesheet" href="../../css/table.css">
    <link rel="stylesheet" href="../../css/readerMainStyle.css">
    <link rel="stylesheet" href="../../css/bookCard.css">
</head>
<body>

<jsp:include page="../commoncode/navigation.jsp"/>

<div class="profileContainer" style="width: 90%">
    <div class="basicInfo">
        <h1><fmt:message key="label.book.store"/></h1>
        <div class="container">
            <c:forEach var="bookList" items="${requestScope.bookList}" varStatus="loop">
                <div class="book-card">
                    <div class="card-container">
                        <img src="../../img/effective.jpg" alt="Avatar" class="book-image">
                        <div class="name-overlay"><h3>${bookList.name}</h3> <br>
                            <c:if test="${bookList.quantity > 0}">
                                <p><fmt:message key="label.available"/></p>
                            </c:if>
                            <c:if test="${bookList.quantity == 0}">
                                <p><fmt:message key="label.not.available"/></p>
                            </c:if>
                        </div>
                    </div>
                    <form name="view-book" action="controller" method="post">
                        <input type="hidden" name="command" value="view-book">
                        <input class="book-btn" type="submit" name="view"
                               value="<fmt:message key="button.view"/> "/>
                        <input type="hidden" name="id" value="<c:out value="${bookList.id}"/>"/>
                    </form>
                </div>
            </c:forEach>
        </div>

    </div>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp" />
</body>

</html>
