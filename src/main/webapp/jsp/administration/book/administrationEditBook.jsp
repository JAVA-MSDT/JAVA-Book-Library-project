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
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/scrollTop.jsp"/>

<div class="profileContainer">
    <div class="basicInfo">

        <h1> <fmt:message key="label.add.edit.book"/> </h1>

        <%-- in case of updating an existing book or inserting a new book one of these messages will be displaye--%>
        <c:choose>
            <%-- Update Info --%>
            <c:when test="${ param.operationStatus eq 'updated'}">
                <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message
                        key="message.update.done"/></h2> <br>
            </c:when>
            <c:when test="${ param.operationStatus eq 'updateFail'}">
                <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message
                        key="message.update.not.done"/></h2> <br>
            </c:when>

            <%-- Insert Info --%>
            <c:when test="${param.operationStatus eq 'inserted'}">
                <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message
                        key="message.insert.done"/></h2> <br>
            </c:when>
            <c:when test="${param.operationStatus eq 'insertFail'}">
                <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message
                        key="message.insert.not.done"/></h2> <br>
            </c:when>
        </c:choose>

        <%-- Book Form for adding or editting --%>
        <div class="container">
            <div class="editContainerForm">
                <form id="librarianBookForm" name="administration-update-book" action="controller" method="post">
                    <input type="hidden" name="command" value="administration-update-book">
                    <input type="hidden" name="id"
                           value="${not empty requestScope.editBook.id ? requestScope.editBook.id : ''}">
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.book.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="name"
                                   value="${not empty requestScope.editBook.name ? requestScope.editBook.name : ''}"
                                   pattern="[a-zA-z0-9 ]{1,40)" placeholder="<fmt:message key="label.book.name"/>"
                                   required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.quantity"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="quantity"
                                   value="${ not empty requestScope.editBook.quantity ? requestScope.editBook.quantity : ''}"
                                   pattern="[0-9]+" placeholder="<fmt:message key="label.quantity"/>" required>
                        </div>
                    </div>
                    <div class="row">
                        <input type="submit" value="<fmt:message key="label.update"/>">
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp"/>
</body>

</html>