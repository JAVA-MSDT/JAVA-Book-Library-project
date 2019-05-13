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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/accountBodyStyle.css">
</head>

<body>

<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/navigation.jsp"/>

<%-- User Form for update user role jst for admin to use --%>
<div class="profileContainer">
    <div class="basicInfo">

       <h1><fmt:message key="button.change.role"/> </h1>
        <hr>
        <c:if test="${not empty requestScope.updateDone}">
            <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message key="message.update.done"/></h2>
            <br>
        </c:if>
        <div class="container">
            <div class="editContainerForm">
                <form id="librarianReaderForm" name="admin-update-role" action="controller" method="post">
                    <input type="hidden" name="command" value="admin-update-role">
                    <input type="hidden" name="id" value="${requestScope.editUser.id}">
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="name"
                                   value="${not empty requestScope.editUser.name ? requestScope.editUser.name : ''}"
                                   readonly>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.reader.last.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="last_name"
                                   value="${not empty requestScope.editUser.lastName ? requestScope.editUser.lastName : ''}"
                                   readonly>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.email"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="email"
                                   value="${not empty requestScope.editUser.email ? requestScope.editUser.email : ''}"
                                   readonly>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.reader.role"/></h3>
                        </div>
                        <div class="inputCol">
                            <select name="role">
                                <option value="${requestScope.editUser.role eq 'ADMIN' ? 'ADMIN' : 'ADMIN'}">
                                    <fmt:message key="label.role.admin"/></option>
                                <option value="${requestScope.editUser.role eq 'LIBRARIAN' ? 'LIBRARIAN' : 'LIBRARIAN'}">
                                    <fmt:message key="label.role.librarian"/></option>
                                <option value="${requestScope.editUser.role eq 'READER' ? 'READER' : 'READER'}">
                                    <fmt:message key="label.role.reader"/></option>
                            </select>
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
