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
            <h1>Edit Book:</h1>
            <div class="container">
                            <div class="editContainerForm">
                                <form id="librarianBookForm" name="librarian-update-book" action="controller" method="post">
                                    <input type="hidden" name="command" value="librarian-update-book">
                                    <div class="row">
                                        <div class="labelCol">
                                            <h3 class="label">Book Id:</h3>
                                        </div>
                                        <div class="inputCol">
                                            <input type="text" name="id" value="${requestScope.editBook.id}">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="labelCol">
                                            <h3 class="label">Book Name:</h3>
                                        </div>
                                        <div class="inputCol">
                                            <input type="text" name="name" value="${requestScope.editBook.name}">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="labelCol">
                                            <h3 class="label">Book Quantity:</h3>
                                        </div>
                                        <div class="inputCol">
                                            <input type="text" name="quantity" value="${requestScope.editBook.quantity}">
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