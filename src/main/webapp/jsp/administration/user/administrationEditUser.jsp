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

<div class="profileContainer">
    <div class="basicInfo">

        <h1><fmt:message key="label.add.edit.user"/></h1>

        <%-- in case of updating an existing User or inserting a new User one of these messages will be displaye--%>

        <c:choose>
            <c:when test="${not empty requestScope.updateDone}">
                <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message
                        key="message.update.done"/></h2> <br>
            </c:when>
            <c:when test="${not empty requestScope.insertDone}">
                <h2 class="permission" style="color: green; margin: 20px auto"><fmt:message
                        key="message.insert.done"/></h2> <br>
            </c:when>
        </c:choose>

        <%-- If there is any attempt for a doble submit or page refreshing this message will be displayed--%>
        <c:if test="${not empty requestScope.doubleSubmit}">
            <h2 class="permission" style="color: brown; margin: 20px auto"><fmt:message
                    key="message.item.already.exist"/></h2> <br>
        </c:if>

        <%-- User Form for adding or editting --%>

        <div class="container">
            <div class="editContainerForm">
                <form id="librarianReaderForm" name="administration-update-user" action="controller" method="post">
                    <input type="hidden" name="command" value="administration-update-user">
                    <input type="hidden" name="insert" value="${sessionScope.inserted}">
                    <input type="hidden" name="id" value="${requestScope.editUser.id}">
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="name"
                                   value="${not empty requestScope.editUser.name ? requestScope.editUser.name : ''}"
                                   pattern="[a-zA-z0-9 ]+" placeholder="<fmt:message key="label.user.name"/>" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.reader.last.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="last_name"
                                   value="${not empty requestScope.editUser.lastName ? requestScope.editUser.lastName : ''}"
                                   pattern="[a-zA-z0-9 ]+" placeholder="<fmt:message key="label.user.name"/>" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.email"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="email"
                                   value="${not empty requestScope.editUser.email ? requestScope.editUser.email : ''}"
                                   pattern="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"
                                   placeholder="<fmt:message key="label.email"/>" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.login"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="login"
                                   value="${not empty requestScope.editUser.login ? requestScope.editUser.login : ''}"
                                   pattern="[a-zA-z0-9]+" placeholder="<fmt:message key="label.login"/>" required>
                        </div>
                    </div>

                    <c:if test="${empty requestScope.editUser}">
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"><fmt:message key="label.password"/></h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="password"
                                       value="${not empty requestScope.editUser.password ? requestScope.editUser.password : ''}"
                                       pattern="^([a-zA-Z0-9@*#]{4,10})$"
                                       placeholder="<fmt:message key="label.role.password.disc"/>" required>
                            </div>
                        </div>
                    </c:if>


                    <c:choose>
                        <c:when test="${sessionScope.user.role eq 'ADMIN'}">
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
                        </c:when>
                        <c:when test="${sessionScope.user.role eq 'LIBRARIAN'}">
                            <div class="row">
                                <div class="inputCol">
                                    <input type="hidden" name="role"
                                           value="${not empty requestScope.editUser.role ? requestScope.editUser.role : ''}">
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.reader.blocked"/></h3>
                        </div>
                        <div class="inputCol">
                            <select name="blocked">
                                <option value="${requestScope.editUser.blocked eq 'false' ? 'FALSE' : 'FALSE'}">
                                    <fmt:message key="label.false"/></option>
                                <option value="${requestScope.editUser.blocked eq 'true' ? 'INSERTED' : 'INSERTED'}">
                                    <fmt:message key="label.true"/></option>
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
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp"/>
</body>

</html>
