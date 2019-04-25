<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/readerMainStyle.css">

</head>

<body>
<%@ include file="../constant/librarianNavigation.jsp" %>
<div class="profileContainer">
    <div class="basicInfo">
        <h1>Profile Page:</h1>
        <table class="readerInfo">
            <tr>
                <td>
                    <h2>Name: </h2>
                </td>
                <td>
                    <h3>${requestScope.reader.name} </h3>
                </td>
            </tr>
            <tr>
                <td>
                    <h2>E-mail: </h2>
                </td>
                <td>
                    <h3> ${requestScope.reader.email}</h3>
                </td>
            </tr>
            <tr>
                <td>
                    <h2>Login: </h2>
                </td>
                <td>
                    <h3> ${requestScope.reader.login}</h3>
                </td>
            </tr>
        </table>
        <p class="editInfo"><a href="#">Edit Info</a></p>
    </div>

</div>

<%@ include file="../constant/readerFooter.jsp" %>
</body>

</html>
