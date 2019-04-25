<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/editForm.css">
    <link rel="stylesheet" href="../../css/readerMainStyle.css">
</head>

<body>
    <%@ include file="../constant/librarianNavigation.jsp"%>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1>Edit Reader:</h1>
            <div class="container">
                <div class="editContainerForm">
                    <form id="librarianReaderForm" name="librarian-update-reader" action="controller" method="post">
                        <input type="hidden" name="command" value="librarian-update-reader">
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label">Reader Id:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="id" value="${requestScope.readerEdit.id}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Name:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="name" value="${requestScope.readerEdit.name}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Last Name:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="last_name" value="${requestScope.readerEdit.lastName}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label">E-mail:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="email" value="${requestScope.readerEdit.email}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Login:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="login" value="${requestScope.readerEdit.login}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Password:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="password" value="${requestScope.readerEdit.password}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Blocked:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="blocked" value="${requestScope.readerEdit.blocked}">
                                <%--<select name="blocked">
                                    <option value="false">${requestScope.readerEdit.blocked}</option>
                                    <option value="true">${requestScope.readerEdit.blocked}</option>
                                </select>--%>
                            </div>
                        </div>
                        <div class="row">
                            <input type="submit" value="Save Update">
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
    <%@ include file="../constant/readerFooter.jsp"%>
</body>

</html>
