<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<li><a href="#"><fmt:message key="nav.news"/></a></li>
<li><a href="#"><fmt:message key="nav.contact"/></a></li>
<li><a href="#"><fmt:message key="nav.about"/></a></li>
