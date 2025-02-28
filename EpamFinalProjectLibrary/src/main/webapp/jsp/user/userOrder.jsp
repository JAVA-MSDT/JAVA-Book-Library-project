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
    <link rel="stylesheet" href="../../css/readerMainStyle.css">
    <link rel="stylesheet" href="../../css/table.css">
</head>

<body>
<%@ include file="../commoncode/userNavigation.jsp" %>
<div class="profileContainer">
    <div class="basicInfo">
        <h1>My Order:</h1>
        <div class="container">
            <div class="tableContainer">
                <table class="tableList">
                    <tr>
                        <th>
                            <h2> Id </h2>
                        </th>
                        <th>
                            <h2> Name</h2>
                        </th>
                        <th>
                            <h2> Order Date </h2>
                        </th>
                        <th>
                            <h2> Returning Date </h2>
                        </th>
                    </tr>
                    <c:forEach varStatus="loop" var="orderList" items="${requestScope.orderList}">
                        <tr>
                            <th>
                                <h2> ${loop.count}</h2>
                            </th>
                            <th>
                                <h2> Name</h2>
                            </th>
                            <th>
                                <h2> ${orderList.orderDate} </h2>
                            </th>
                            <th>
                                <h2> ${orderList.returningDate} </h2>
                            </th>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>

    </div>

</div>
<%@ include file="../commoncode/userFooter.jsp" %>
</body>

</html>
