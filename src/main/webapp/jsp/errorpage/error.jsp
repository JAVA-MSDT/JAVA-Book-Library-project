<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<jsp:include page="../commoncode/navigation.jsp"/>

<div style="margin: 0 auto">
    <h1><fmt:message key="message.nullPage"/></h1>
    <c:if test="${not empty requestScope.error}">
        <c:out value="${requestScope.error}"/>
    </c:if>
</div>
<jsp:include page="../commoncode/footer.jsp"/>
</body>
</html>