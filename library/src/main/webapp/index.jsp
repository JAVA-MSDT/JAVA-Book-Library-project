<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html lang="${sessionScope.language}">

<head>
    <title> <fmt:message key="label.title.library"/> </title>
    <meta charset="utf-8">
    <meta name="description" content="Library">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>

<body>
<jsp:include page="/jsp/commoncode/navigation.jsp"/>
<jsp:include page="/jsp/commoncode/scrollTop.jsp"/>

<div class="mainRow">
    <div class=libraryImage>
        <h2>Library</h2>
        <img src="${pageContext.request.contextPath}/img/book.jpg"  width="400" height="250" alt="Logo">
    </div>
    <div class="libraryText">
        <h2>News</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis
            velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat,
            purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan
            convallis.</p>

        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis
            velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat,
            purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan
            convallis.</p>
    </div>
</div>

<div class="secondRow">
    <div class="columnTwo">
        <h2>Library</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis
            velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat,
            purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan
            convallis.</p>
    </div>
    <div class="columnTwo">
        <h2>News</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis
            velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat,
            purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan
            convallis.</p>
    </div>
    <div class="columnTwo">
        <h2>Awards</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis
            velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat,
            purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan
            convallis.</p>
    </div>
</div>

<jsp:include page="/jsp/commoncode/footer.jsp"/>
</body>
</html>
