<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
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
<jsp:include page="jsp/constant/navigation.jsp"/>
<div class="loginContainer">
    <div class="form">
        <img src="img/epam.jpg" width="100" height="100">
        <h2>Welcome Back</h2>
        <form name="LoginForm" action="controller" method="POST">
            <input type="hidden" name="command" value="Login"/>
            <input type="text" name="login" placeholder="Login">
            <input type="password" name="password" placeholder="Password">
            <input type="submit" value="Login" id="btnLog">
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
<div class="testing">
    <a class="regbtn" href="jsp/reader/readerProfile.jsp"> Reader Profile </a><br/>
    <a class="regbtn" href="jsp/librarian/librarianProfile.jsp"> Librarian Profile </a>

</div>
<%@include file="jsp/constant/footer.html" %>
</body>
</html>