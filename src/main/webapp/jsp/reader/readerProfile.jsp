<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<html>

<head>
    <title>${sessionScope.reader.name}</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/readerMainStyle.css">

</head>

<body>
    <jsp:include page="../constant/readerNavigation.jsp"/>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1> <fmt:message key="label.profile.page"/> </h1>
            <table class="readerInfo">
                <tr>
                    <td>
                        <h2> <fmt:message key="label.name"/> </h2>
                    </td>
                    <td>
                        <h3> ${sessionScope.reader.name}</h3>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2> <fmt:message key="label.email"/> </h2>
                    </td>
                    <td>
                        <h3> ${sessionScope.reader.email}</h3>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2> <fmt:message key="label.login"/> </h2>
                    </td>
                    <td>
                        <h3> ${sessionScope.reader.login}</h3>
                    </td>
                </tr>

            </table>
            <p class="editInfo"><a href="#"> <fmt:message key="button.edit"/> </a></p>
        </div>

    </div>

    <jsp:include page="../constant/readerFooter.jsp"/>
</body>

</html>
