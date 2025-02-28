<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../../css/readerMainStyle.css">
    <link rel="stylesheet" href="../../../css/table.css">
</head>

<body>
<jsp:include page="/jsp/commoncode/librarianNavigation.jsp"/>
<div class="profileContainer">
    <div class="basicInfo">
        <h1><fmt:message key="label.reader.list"/></h1>
        <div class="btnContainer">
            <form name="librarian-add-user" action="controller" method="post">
                <input type="hidden" name="command" value="librarian-add-user">
                <input class="add-button" type="submit" name="edit" value="<fmt:message key="label.add.reader"/>"/>
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
                            <h3><fmt:message key="label.reader.last.name"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.email"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.login"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.password"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="label.reader.blocked"/></h3>
                        </th>
                        <th>
                            <h3><fmt:message key="button.edit"/></h3>
                        </th>

                    </tr>

                    <c:forEach varStatus="loop" var="userList" items="${requestScope.userList}">
                        <tr>
                            <td>${loop.count}</td>
                            <td>${userList.name}</td>
                            <td>${userList.lastName}</td>
                            <td>${userList.email}</td>
                            <td>${userList.login}</td>
                            <td>${userList.password}</td>
                            <td>${userList.blocked}</td>
                            <td>
                                <form name="librarian-edit-user" action="controller" method="post">
                                    <input type="hidden" name="command" value="librarian-edit-user">
                                    <input class="edit" type="submit" name="edit"
                                           value="<fmt:message key="button.edit"/>"/>
                                    <input type="hidden" name="id" value="<c:out value="${userList.id}"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>

    </div>

</div>
<jsp:include page="/jsp/commoncode/userFooter.jsp"/>
</body>

</html>
