<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>

<head>
    <title><fmt:message key="label.title.epam"/></title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../../css/editForm.css">
    <link rel="stylesheet" href="../../../css/readerMainStyle.css">
</head>

<body>

<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/navigation.jsp"/>

<div class="profileContainer">
    <div class="basicInfo">

       <h1><fmt:message key="button.change.role"/> </h1>

        <div class="container">
            <div class="editContainerForm">
                <form id="librarianReaderForm" name="administration-update-role" action="controller" method="post">
                    <input type="hidden" name="command" value="administration-update-role">
                    <input type="hidden" name="id" value="${requestScope.editUser.id}">
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="name"
                                   value="${not empty requestScope.editUser.name ? requestScope.editUser.name : ''}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.reader.last.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="last_name"
                                   value="${not empty requestScope.editUser.lastName ? requestScope.editUser.lastName : ''}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.email"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="email"
                                   value="${not empty requestScope.editUser.email ? requestScope.editUser.email : ''}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.login"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="login"
                                   value="${not empty requestScope.editUser.login ? requestScope.editUser.login : ''}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.password"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="password"
                                   value="${not empty requestScope.editUser.password ? requestScope.editUser.password : ''}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.reader.blocked"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="blocked"
                                   value="${not empty requestScope.editUser.blocked ? requestScope.editUser.blocked : ''}">
                            <%--<select name="blocked">
                                <option value="false">${requestScope..blocked}</option>
                                <option value="true">${requestScope..blocked}</option>
                            </select>--%>
                        </div>
                    </div>
                    <div class="row">
                        <input type="submit" value="<fmt:message key="label.update"/>">
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp" />
</body>

</html>
