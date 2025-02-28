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
        <h1><fmt:message key="label.edit.book"/></h1>

        <div class="container">
            <div class="editContainerForm">
                <form id="librarianBookForm" name="librarian-update-book" action="controller" method="post">
                    <input type="hidden" name="command" value="librarian-update-book">
                    <input type="hidden" name="id" value="${requestScope.editBook.id}">
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="name" value="${requestScope.editBook.name}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.quantity"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="quantity" value="${requestScope.editBook.quantity}">
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
<jsp:include page="/jsp/commoncode/userFooter.jsp"/>
</body>

</html>