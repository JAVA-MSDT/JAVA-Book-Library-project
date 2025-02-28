<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="role" class="com.epam.library.entity.enumeration.Role"
             type="com.epam.library.entity.enumeration.Role"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<c:if test="${}">

</c:if>
<header class="infoHeader">
    <div class="avContainer">
        <img class="avatar" src="../../img/profImage.jpg">
        <div class="overlay">
            <form name="edit-profile-image" action="controller" method="post">
                <input type="hidden" name="command" value="edit-profile-image">
                <input class="add-button" type="submit" name="edit" value="<fmt:message key="button.edit"/>"/>
            </form>
        </div>
    </div>

    <h2>${sessionScope.user.name}</h2>
    <h2> ${sessionScope.user.lastName}</h2>
</header>
