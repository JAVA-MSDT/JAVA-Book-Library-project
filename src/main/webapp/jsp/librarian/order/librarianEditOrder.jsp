<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>

<head>
    <title> <fmt:message key="label.title.epam"/> </title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../../css/editForm.css">
    <link rel="stylesheet" href="../../../css/readerMainStyle.css">
</head>

<body>
<jsp:include page="/jsp/constant/librarianNavigation.jsp"/>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1> <fmt:message key="label.edit.order"/> </h1>
            <div class="container">
                <div class="editContainerForm">
                    <form id="librarianOrderForm" name="librarian-update-order" action="controller" method="post">
                        <input type="hidden" name="command" value="librarian-update-order">
                        <input type="hidden" name="id" value="${requestScope.editOrder.orderId}">

                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> <fmt:message key="label.order.book.id"/> </h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="book_id" value="${requestScope.editOrder.bookId}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> <fmt:message key="label.order.reader"/> </h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="user_id" value="${requestScope.editOrder.userId}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> <fmt:message key="label.order.date"/> </h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="order_date" value="${requestScope.editOrder.orderDate}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> <fmt:message key="label.order.return.date"/> </h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="returning_date" value="${requestScope.editOrder.returningDate}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> <fmt:message key="label.order.reading.place"/> </h3>
                            </div>
                            <div class="inputCol">

                                <input type="text" name="reading_place" value="${requestScope.editOrder.readingPlace}">
                                <%--<select id="librarianReadingPlace" name="reading_place">
                                <option value="HOME">Home</option>
                                <option value="HALL">Hall</option>
                                </select>--%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> <fmt:message key="label.order.returned"/> </h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="book_returned" value="${requestScope.editOrder.bookReturned}">
                            </div>
                        </div>
                        <div class="row">
                            <input type="submit" value="<fmt:message key="label.update"/> ">
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
<jsp:include page="/jsp/constant/readerFooter.jsp"/>
</body>

</html>
