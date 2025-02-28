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
<jsp:include page="/jsp/commoncode/librarianNavigation.jsp"/>
<div class="profileContainer">
    <div class="basicInfo">
        <h1><fmt:message key="label.add.reader"/></h1>
        <div class="container">
            <div class="editContainerForm">
                <form id="librarianReaderForm" name="librarian-update-user" action="controller" method="post">
                    <input type="hidden" name="command" value="librarian-update-user">
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="name" value="" pattern="[a-zA-Z ]*">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.reader.last.name"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="last_name" value="" pattern="[a-zA-Z ]*">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.email"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="email" value=""
                                   pattern="^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.login"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="login" value="" pattern="[a-zA-z0-9]+">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.password"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="password" value="" pattern="^([a-zA-Z0-9@*#]{4,10})$">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.reader.blocked"/></h3>
                        </div>
                        <div class="inputCol">
                            <select name="blocked">
                                <option value="false"><fmt:message key="label.false"/></option>
                                <option value="true"><fmt:message key="label.true"/></option>
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
<jsp:include page="/jsp/commoncode/userFooter.jsp"/>
</body>

</html>
