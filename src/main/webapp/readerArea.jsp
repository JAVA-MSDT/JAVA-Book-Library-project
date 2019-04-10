<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
     <title> Reader Area</title>
    <meta charset="utf-8">
    <meta name="description" content="Readr Area">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale = 1.0">
    <meta name="keywords" content="reader, login, logout, signin, signout, regester">
    <link rel="stylesheet" href="../css/login.css" type="text/css">
    <link rel="stylesheet" href="../css/index.css" type="text/css">
</head>
<body>
<%@ include file="/jsp/menuBar.jsp"%>
    <h1>Reader Area</h1>
    <fieldset id="fieldSet">
        <legend> Login </legend>
        <form id = "userLogin" name= "LoginForm" action="controller" method="POST">
        <input type="hidden" name= "command" value= "Login"/>
            Login: <br>
            <input type="text" name="login"> <br>
            Password: <br>
            <input type="password" name="password"><br>
            <input type="submit" value="Login">
            <input type="submit" value="Registration">
        </form>
    </fieldset>
</body>
</html>