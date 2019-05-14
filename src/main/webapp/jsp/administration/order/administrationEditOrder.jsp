<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>

<head>
    <title><fmt:message key="label.title.epam"/></title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/accountBodyStyle.css">
</head>

<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/navigation.jsp"/>

<div class="profileContainer">
    <div class="basicInfo">

        <h1> <fmt:message key="label.add.edit.order"/> </h1>
        <hr>

        <%-- in case of updating an existing order or inserting a new order one of these messages will be displaye--%>

        <c:choose>
            <c:when test="${not empty requestScope.updateDone}">
                <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message
                        key="message.update.done"/></h2> <br>
            </c:when>
            <c:when test="${not empty requestScope.insertDone}">
                <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message
                        key="message.insert.done"/></h2> <br>
            </c:when>
        </c:choose>

        <%-- If there is any attempt for a doble submit or page refreshing this message will be displayed--%>
        <c:if test="${not empty requestScope.doubleSubmit}">
            <h2 class="permission" style="color: brown; margin: 20px auto"><fmt:message
                    key="message.item.already.exist"/></h2> <br>
        </c:if>

        <%-- Order Form for adding or editting --%>

        <div class="container">
            <div class="editContainerForm">
                <form id="librarianOrderForm" name="administration-update-order" action="controller" method="post">
                    <input type="hidden" name="command" value="administration-update-order">
                    <input type="hidden" name="insert" value="${sessionScope.inserted}">
                    <input type="hidden" name="id" value="${requestScope.editOrder.orderId}">

                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.book.id"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="book_id"
                                   value="${not empty requestScope.editOrder.bookId ? requestScope.editOrder.bookId : ''}"
                                   pattern="[0-9]+" placeholder="<fmt:message key="label.order.book.id"/>" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.reader"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="user_id"
                                   value="${not empty requestScope.editOrder.userId ? requestScope.editOrder.userId : ''}"
                                   pattern="[0-9]+" placeholder="<fmt:message key="label.order.reader"/>" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.date"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="date" name="order_date"
                                   value="${not empty requestScope.editOrder.orderDate ? requestScope.editOrder.orderDate : ''}"
                                   placeholder="<fmt:message key="label.order.date"/>" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.return.date"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="date" name="returning_date"
                                   value="${not empty requestScope.editOrder.returningDate ? requestScope.editOrder.returningDate : ''}"
                                   placeholder="<fmt:message key="label.order.return.date"/>" required>

                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.reading.place"/></h3>
                        </div>
                        <div class="inputCol">
                            <select id="librarianReadingPlace" name="reading_place">
                                <option value=" ${requestScope.editOrder.readingPlace eq 'HOME' ? 'HOME' : 'HOME'}">
                                    <fmt:message key="label.order.home"/>
                                </option>
                                <option value="${requestScope.editOrder.readingPlace eq 'HALL' ? 'HALL' : 'HALL'}">
                                    <fmt:message key="label.order.hall"/>
                                </option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.returned"/></h3>
                        </div>
                        <div class="inputCol">
                            <select id="book-return" name="book_returned">
                                <option value=" ${requestScope.editOrder.bookReturned eq  'false' ? 'false' : 'false'}">
                                    <fmt:message key="label.false"/>
                                </option>
                                <option value="${requestScope.editOrder.bookReturned eq 'true' ? 'true' : 'true'}">
                                    <fmt:message key="label.true"/>
                                </option>
                            </select>
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
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp" />
</body>

</html>
