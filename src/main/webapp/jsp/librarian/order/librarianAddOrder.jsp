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
    <link rel="stylesheet" href="../../../css/editForm.css">
    <link rel="stylesheet" href="../../../css/readerMainStyle.css">
</head>

<body>
<jsp:include page="/jsp/commoncode/librarianNavigation.jsp"/>
<div class="profileContainer">
    <div class="basicInfo">
        <h1><fmt:message key="label.add.order"/></h1>
        <div class="container">
            <div class="editContainerForm">
                <form id="librarianOrderForm" name="librarian-update-order" action="controller" method="post">
                    <input type="hidden" name="command" value="librarian-update-order">
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.book.id"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="book_id" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.reader"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="user_id" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.date"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="order_date" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.return.date"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="returning_date" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.reading.place"/></h3>
                        </div>
                        <div class="inputCol">

                            <select id="librarianReadingPlace" name="reading_place">
                                <option value="HOME"><fmt:message key="label.order.home"/></option>
                                <option value="HALL"><fmt:message key="label.order.hall"/></option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.returned"/></h3>
                        </div>
                        <div class="inputCol">
                            <%-- <input type="text" name="book_returned" value="">--%>
                            <select id="returned" name="book_returned">
                                <option value="false"><fmt:message key="label.false"/></option>
                                <option value="true"><fmt:message key="label.true"/></option>
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
<jsp:include page="/jsp/commoncode/userFooter.jsp"/>
</body>

</html>
