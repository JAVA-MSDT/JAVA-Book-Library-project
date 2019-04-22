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
    <link rel="stylesheet" href=".../../../../css/mainStyle.css">
    <link rel="stylesheet" href=".../../../../css/tableStyle.css">
</head>

<body>
    <%@ include file="../constant/librarianNavigation.jsp"%>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1>Readers List:</h1>
             <div class="btnContainer">
                 <a href="librarianEditReader.jsp" name="librarianAddReader"> Add Reader </a>
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
                                <h3> Last Name </h3>
                            </th>
                            <th>
                                <h3> E-mail </h3>
                            </th>
                            <th>
                                <h3> Login </h3>
                            </th>
                            <th>
                                <h3> Password</h3>
                            </th>
                            <th>
                                <h3> Blocked </h3>
                            </th>
                            <th>
                                <h3> Edit </h3>
                            </th>

                        </tr>

                        <c:forEach var="userList" items="${requestScope.usersList}">
                            <tr>
                                <td>${userList.id}</td>
                                <td>${userList.name}</td>
                                <td>${userList.lastName}</td>
                                <td>${userList.email}</td>
                                <td>${userList.login}</td>
                                <td>${userList.password}</td>
                                <td>${userList.blocked}</td>
                                <td> <a class="edit" href="controller?command=libeditreader">Edit</a></td>
                                <td><form name="libEditReader" action="controller" method="post">
                                    <input type="hidden" name="command" value="libeditreader">
                                    <input class="edit" type="submit" name="edit" value="Edit"/>
                                    <input type="hidden" name="id" value="<c:out value="${userList.id}"/>"/>
                                </form> </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </div>

        </div>

    </div>
    <%@ include file="../constant/readerFooter.jsp"%>
</body>

</html>
