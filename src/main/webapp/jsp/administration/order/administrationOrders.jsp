<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>

<head>
    <title><fmt:message key="label.title.epam"/></title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../../css/readerMainStyle.css">
    <link rel="stylesheet" href="../../../css/table.css">
    <link rel="stylesheet" href="../../../css/bookCard.css">
</head>

<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/navigation.jsp"/>

<div class="profileContainer">
    <div class="basicInfo">
        <h1><fmt:message key="label.order.list"/></h1>
        <div class="btnContainer">
            <form name="administration-edit-order" action="controller" method="post">
                <input type="hidden" name="command" value="administration-edit-order">
                <input class="add-button" type="submit" name="add" value="<fmt:message key="label.add.order"/>"/>
            </form>
        </div>
        <div class="container">
            <div class="tableContainer">
                <table class="tableList">
                    <tr>
                        <th>
                            <h3><fmt:message key="label.id"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.name"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.email"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.name"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.order.date"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.order.return.date"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.order.reading.place"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.order.returned"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="button.edit"/></h3>
                        </th>
                    </tr>
                    <c:forEach varStatus="loop" var="row" items="${requestScope.orderList}">
                        <tr>
                            <td><h4>${loop.count}</h4></td>
                            <td><h4>${row.userName}</h4></td>
                            <td><h4>${row.userEmail}</h4></td>
                            <td><h4>${row.bookName}</h4></td>
                            <td><h4>${row.orderDate}</h4></td>
                            <td><h4>${row.returningDate}</h4></td>
                            <td><h4>${row.readingPlace}</h4></td>
                            <td><h4>${row.bookReturned}</h4></td>
                            <td>
                                <form name="administration-edit-order" action="controller" method="post">
                                    <input type="hidden" name="command" value="administration-edit-order">
                                    <input class="edit" type="submit" name="edit"
                                           value="<fmt:message key="button.edit"/>"/>
                                    <input type="hidden" name="id" value="<c:out value="${row.id}"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <br>

    </div>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp" />
</body>

</html>
