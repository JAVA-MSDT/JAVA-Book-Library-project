<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>

<head>
    <title><fmt:message key="label.title.epam"/> </title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../../css/readerMainStyle.css">
    <link rel="stylesheet" href="../../../css/table.css">
    <link rel="stylesheet" href="../../../css/bookCard.css">
</head>

<body>
<jsp:include page="/jsp/constant/librarianNavigation.jsp"/>
<div class="profileContainer">
    <div class="basicInfo">
        <h1> <fmt:message key="label.order.list"/> </h1>
        <div class="btnContainer">
            <form name="librarian-add-order" action="controller" method="post">
                <input type="hidden" name="command" value="librarian-add-order">
                <input class="add-button" type="submit" name="add" value="<fmt:message key="label.add.order"/>"/>
            </form>
        </div>
        <div class="container">
            <div class="tableContainer">
                <table class="tableList">
                    <tr>
                        <th>
                            <h3> <fmt:message key="label.id"/> </h3>
                        </th>
                        <th>
                            <h3> <fmt:message key="label.order.book.id"/></h3>
                        </th>
                        <th>
                            <h3> <fmt:message key="label.order.reader"/> </h3>
                        </th>
                        <th>
                            <h3> <fmt:message key="label.order.date"/> </h3>
                        </th>
                        <th>
                            <h3> <fmt:message key="label.order.return.date"/> </h3>
                        </th>
                        <th>
                            <h3> <fmt:message key="label.order.reading.place"/> </h3>
                        </th>
                        <th>
                            <h3> <fmt:message key="label.order.returned"/> </h3>
                        </th>
                        <th>
                            <h3> <fmt:message key="button.edit"/> </h3>
                        </th>

                    </tr>
                    <c:forEach varStatus="loop" var="orders" items="${requestScope.orderList}">
                        <tr>
                            <td><h4>${loop.count}</h4></td>
                            <td><h4>${orders.bookId}</h4></td>
                            <td><h4>${orders.userId}</h4></td>
                            <td><h4>${orders.orderDate}</h4></td>
                            <td><h4>${orders.returningDate}</h4></td>
                            <td><h4>${orders.readingPlace}</h4></td>
                            <td><h4>${orders.bookReturned}</h4></td>
                            <td>
                                <form name="librarian-edit-order" action="controller" method="post">
                                    <input type="hidden" name="command" value="librarian-edit-order">
                                    <input class="edit" type="submit" name="edit" value="<fmt:message key="button.edit"/>"/>
                                    <input type="hidden" name="id" value="<c:out value="${orders.orderId}"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

    </div>

    <div class="book-card">
        <div class="card-container">
            <div class="card-image">
                <img src="../../../img/profImage.jpg">
            </div>
            <div class="book-name">
                <h2>Book Name: </h2> <h3> Book Name</h3>
            </div>
            <div class="book-author">
                    <h2>Book Author: </h2> <h3>Book Author</h3>
            </div>
            <div class="book-page">
                <div class="btn-container">
                    <form name="book-page" action="controller" method="post">
                        <input type="hidden" name="command" value="book-page">
                        <input class="book-btn" type="submit" name="add" value="Book Page"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/constant/readerFooter.jsp"/>
</body>

</html>
