<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>${sessionScope.reader.name}</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/table.css">
    <link rel="stylesheet" href="../../css/readerMainStyle.css">
    <link rel="stylesheet" href="../../../css/book.css">
</head>

<body>
<jsp:include page="../constant/readerNavigation.jsp" />
<div class="profileContainer">
    <div class="basicInfo">
        <h1><fmt:message key="label.book.store"/></h1>
        <div class="container">
                <c:forEach var="bookList" items="${requestScope.bookList}" varStatus="loop">
                <div class="book-card">
                    <div class="card-container">
                        <img src="../../img/effective.jpg" alt="Avatar" class="book-image">
                        <div class="name-overlay"><h3>${bookList.name}</h3> <br>
                            <c:if test="${bookList.quantity > 0}">
                                <p> <fmt:message key="label.available"/> </p>
                            </c:if>
                            <c:if test="${bookList.quantity == 0}">
                                <p> <fmt:message key="label.not.available"/> </p>
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
               <%-- <table class="tableList">
                    <tr>
                        <th>
                            <h3><fmt:message key="label.id"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.name"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.availability"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.view.book"/></h3>
                        </th>
                    </tr>
                    <c:forEach var="bookList" items="${requestScope.bookList}" varStatus="loop">


                        <tr>
                            <td>
                                <h3> ${loop.count} </h3>
                            </td>
                            <td>
                                <h3> ${bookList.name} </h3>
                            </td>
                            <td>
                                <c:if test="${bookList.quantity > 0}">
                                    <h3> <fmt:message key="label.available"/> </h3>
                                </c:if>
                                <c:if test="${bookList.quantity == 0}">
                                    <h3> <fmt:message key="label.not.available"/> </h3>
                                </c:if>
                            </td>
                            <td>
                                <form name="view-book" action="controller" method="post">
                                    <input type="hidden" name="command" value="view-book">
                                    <input class="edit" type="submit" name="edit"
                                           value="<fmt:message key="button.view"/> "/>
                                    <input type="hidden" name="id" value="<c:out value="${bookList.id}"/>"/>
                                </form>
                            </td>
                            &lt;%&ndash;<td>
                                <form name="order-book" action="controller" method="post">
                                    <input type="hidden" name="command" value="order-book">
                                    <input class="edit" type="submit" name="order"
                                           value="<fmt:message key="button.order"/> "/>
                                    <input type="hidden" name="id" value="<c:out value="${bookList.id}"/>"/>
                                </form>
                            </td>
                            <td>
                                <form name="cancel-book" action="controller" method="post">
                                    <input type="hidden" name="command" value="cancel-book">
                                    <input class="edit" type="submit" name="edit"
                                           value="<fmt:message key="button.cancel"/> "/>
                                    <input type="hidden" name="id" value="<c:out value="${bookList.id}"/>"/>
                                </form>
                            </td>&ndash;%&gt;
                        </tr>
                    </c:forEach>
                </table>--%>
        </div>

    </div>

</div>
<jsp:include page="../constant/readerFooter.jsp" />
</body>

</html>
