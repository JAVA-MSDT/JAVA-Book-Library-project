<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>

<body>
<jsp:include page="/jsp/constant/librarianNavigation.jsp"/>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1> <fmt:message key="label.book.store"/> </h1>
            <div class="btnContainer">
                <form name="librarian-add-book" action="controller" method="post">
                    <input type="hidden" name="command" value="librarian-add-book">
                    <input class="add-button" type="submit" name="add" value="<fmt:message key="label.add.book"/>"/>
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
                                <h3> <fmt:message key="label.name"/> </h3>
                            </th>
                            <th>
                                <h3> <fmt:message key="label.quantity"/> </h3>
                            </th>
                            <th>
                                <h3> <fmt:message key="button.edit"/> </h3>
                            </th>
                        </tr>
                        <c:forEach varStatus="loop" var="bookList" items="${requestScope.bookList}">
                        <tr>
                            <td>
                                <h4> ${loop.count} </h4>
                            </td>
                            <td>
                                <h4> ${bookList.name}</h4>
                            </td>
                            <td> <h4> ${bookList.quantity} </h4> </td>
                            <td>
                                <form name="librarian-edit-book" action="controller" method="post">
                                    <input type="hidden" name="command" value="librarian-edit-book">
                                    <input class="edit" type="submit" name="edit" value="<fmt:message key="button.edit"/> "/>
                                    <input type="hidden" name="id" value="<c:out value="${bookList.id}"/>"/>
                                </form>
                            </td>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </div>

    </div>
<jsp:include page="/jsp/constant/readerFooter.jsp"/>
</body>

</html>