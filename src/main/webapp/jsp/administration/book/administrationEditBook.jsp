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
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/navigation.jsp"/>
<div class="profileContainer">
    <div class="basicInfo">

<c:choose>
    <c:when test="${requestScope.editBook.id eq null}">
        <h1><fmt:message key="label.add.book"/> </h1>
    </c:when>
    <c:otherwise>
        <h1><fmt:message key="label.edit.book"/></h1>
    </c:otherwise>
</c:choose>

        <div class="container">
            <div class="editContainerForm">
                <form id="librarianBookForm" name="administration-update-book" action="controller" method="post">
                    <input type="hidden" name="command" value="administration-update-book">
                    <input type="hidden" name="id"
                           value="${not empty requestScope.editBook.id ? requestScope.editBook.id : ''}">
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="name"
                                   value="${not empty requestScope.editBook.name ? requestScope.editBook.name : ''}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.quantity"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="quantity"
                                   value="${ not empty requestScope.editBook.quantity ? requestScope.editBook.quantity : ''}">
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
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp" />
</body>

</html>