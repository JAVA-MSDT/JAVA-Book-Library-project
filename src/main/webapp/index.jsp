<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="description" content="Epam Library">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/index.css">
</head>

<body>
<header>
    <img src="img/book.jpg" class="headerImage" alt="header image">
</header>

<jsp:include page="jsp/constant/navigation.jsp"/>
<div class="mainRow">
    <div class="epamImage">
        <h2>Library</h2>
        <img src="img/epam.jpg" width="450" height="300" alt="Epam Logo">
    </div>
    <div class="epamText">
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

<%@include file="/jsp/constant/footer.html" %>
</body>
</html>
