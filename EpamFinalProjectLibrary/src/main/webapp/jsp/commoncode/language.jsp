<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../../css/navigation.css">

<li style="float: right" class="dropdown">
    <a class="dropbtn"> <fmt:message key="nav.language"/> </a>
    <div class="dropdown-content">
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="change-language"/>
            <button type="submit" name="language"
                    value="en"><fmt:message key="nav.language.en"/>
            </button>
            <button type="submit" name="language"
                    value="de"><fmt:message key="nav.language.de"/>
            </button>
            <button type="submit" name="language"
                    value="ar"><fmt:message key="nav.language.ar"/>
            </button>
            <button type="submit" name="language"
                    value="ru"><fmt:message key="nav.language.ru"/>
            </button>
        </form>
    </div>
</li>