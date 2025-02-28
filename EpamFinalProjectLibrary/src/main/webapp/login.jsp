<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="description" content="Epam Library">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<jsp:include page="jsp/commoncode/siteNavigation.jsp"/>

<div class="loginContainer">
    <div class="form">

        <img src="img/epam.jpg" width="100" height="100">
        <h2>Welcome Back</h2>

        <div class="error-message">
            <c:if test="${not empty requestScope.invalidLogin}">
                <h2 style="color: brown"><fmt:message key="message.loginError"/></h2>
            </c:if>
            <c:if test="${not empty requestScope.blocked}">
                <h2 style="color: brown"><fmt:message key="message.blockedUser"/></h2>
            </c:if>
        </div>

        <form name="LoginForm" action="controller" method="POST">
            <input type="hidden" name="command" value="Login"/>
            <input type="text" placeholder=
            <fmt:message key="label.login"/> name="login" pattern="[a-zA-z0-9]+"/>
            <input type="password" placeholder=
            <fmt:message key="label.password"/> name="password" pattern="^([a-zA-Z0-9@*#]{4,10})$"/>
            <input type="submit" value=
            <fmt:message key="label.login"/> id="btnLog">
        </form>
        <div class="register">
						<span class="txt1">
							Don’t have an account?
						</span>

            <a class="regbtn" href="#">
                Register
            </a>
        </div>

    </div>
</div>
<%@include file="jsp/commoncode/footer.jsp" %>
</body>
</html>