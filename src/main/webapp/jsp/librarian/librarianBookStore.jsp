<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/readerMainStyle.css">
    <link rel="stylesheet" href="../../css/table.css">
</head>

<body>
    <%@ include file="../constant/librarianNavigation.jsp"%>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1>Book Store:</h1>
            <div class="btnContainer">
                <form name="librarian-add-book" action="controller" method="post">
                    <input type="hidden" name="command" value="librarian-add-book">
                    <input class="add-button" type="submit" name="add" value="Add Book"/>
                </form>
            </div>
            <div class="container">
                <div class="tableContainer">
                    <table class="tableList">
                        <tr>
                            <th>
                                <h3> Id </h3>
                            </th>
                            <th>
                                <h3> Name</h3>
                            </th>
                            <th>
                                <h3> Quantity </h3>
                            </th>
                            <th>
                                <h3> Edit </h3>
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
                                    <input class="edit" type="submit" name="edit" value="Edit"/>
                                    <input type="hidden" name="id" value="<c:out value="${bookList.id}"/>"/>
                                </form>
                            </td>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </div>

    </div>
    <%@ include file="../constant/readerFooter.jsp"%>
</body>

</html>