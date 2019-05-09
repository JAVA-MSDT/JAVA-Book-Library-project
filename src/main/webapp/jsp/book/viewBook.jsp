<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>
        <c:if test="${ not empty sessionScope.user}">
            ${requestScope.book.name}
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <fmt:message key="label.title.epam"/>
        </c:if>
    </title>
    <link rel="stylesheet" href="../../css/viewBook.css"/>
    <link rel="stylesheet" href="../../css/editForm.css"/>
</head>
<body>
<jsp:include page="../commoncode/navigation.jsp"/>

<div class="main-container">
    <div class="tiny-header">
        <p> ${requestScope.book.name} </p>
    </div>

    <c:if test="${not empty requestScope.invalidLogin}">
        <h2 class="permission"><fmt:message key="message.login.register"/></h2> <br>
    </c:if>

    <c:if test="${not empty requestScope.done}">
        <h2 class="permission" style="color: green"><fmt:message key="message.book.order.done"/></h2> <br>
    </c:if>

    <div class="book-container">
        <div class="book-img">
            <img alt="Effective Java" src="../../img/effective.jpg"/>

        </div>
        <div class="book-description">
            <div class="book-title">
                <h2> ${requestScope.book.name} </h2>
            </div>
            <div class="book-author">
                <h2>By: <span>Joshua Bloch</span></h2>
            </div>
            <div class="about-book"><h3> About the Book </h3></div>
            <div class="book-descr">
                <p>&nbsp;&nbsp;&nbsp;&nbsp;It is a long established fact that a reader will be distracted by the
                    readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has
                    a more-or-less normal distribution of letters, as opposed to using 'Content here, content here',
                    making it look like readable English. Many desktop publishing packages and web page editors now use
                    Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites
                    still in their infancy. Various versions have evolved over the years, sometimes by accident,
                    sometimes on purpose (injected humour and the like). </p>
            </div>
            <div class="book-info">
                <table class="book-table-info">
                    <tr>
                        <th colspan="2"><h3>Author Info</h3></th>
                    </tr>
                    <tr>
                        <th> Nationality</th>
                        <td> earth</td>
                    </tr>
                    <tr>
                        <th>Birth date</th>
                        <td>0000</td>
                    </tr>
                    <tr>
                        <th>Works</th>
                        <td> Link</td>
                    </tr>
                    <tr>
                        <th colspan="2"><h3>Edition Notes:</h3></th>
                    </tr>
                    <tr>
                        <th> Edition Info:</th>
                        <td> First Edition</td>
                    </tr>
                    <tr>
                        <th>Genre:</th>
                        <td> Programming</td>
                    </tr>
                    <tr>
                        <th>Copyright Date:</th>
                        <td> 2000</td>
                    </tr>
                </table>
            </div>
            <div class="order-button-container" style="margin-top: 25px">
                <form name="order-book" action="controller" method="post">
                    <input type="hidden" name="command" value="order-book">
                    <input style="width: 200px" class="edit" type="submit" name="order"
                           value="<fmt:message key="button.order"/> "/>
                    <input type="hidden" name="id" value="<c:out value="${requestScope.book.id}"/>"/>
                </form>
            </div>
        </div>
    </div>

    <!-- if the user if logged in and he is authorised, this part wil be display -->
    <c:if test="${not empty requestScope.display}">
        <div class="container">
            <div class="editContainerForm">
                <form id="librarianOrderForm" name="confirm-order" action="controller" method="post">
                    <input type="hidden" name="command" value="confirm-order">
                    <input type="hidden" name="id" value="<c:out value="${requestScope.book.id}"/>"/>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.date"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="order_date" value="" placeholder="YYYY-MM-DD">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.return.date"/></h3>
                        </div>
                        <div class="inputCol">
                            <input type="text" name="returning_date" value="" placeholder="YYYY-MM-DD">
                        </div>
                    </div>
                    <div class="row">
                        <div class="labelCol">
                            <h3 class="label"><fmt:message key="label.order.reading.place"/></h3>
                        </div>
                        <div class="inputCol">
                            <select id="librarianReadingPlace" name="reading_place">
                                <option value="HOME"><fmt:message key="label.order.home"/></option>
                                <option value="HALL"><fmt:message key="label.order.hall"/></option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <input style="width: 200px" class="edit" type="submit" name="order"
                               value="<fmt:message key="button.confirm"/> "/>
                    </div>
                </form>
            </div>
        </div>
    </c:if>

    <hr>
    <div class="container" style="margin-bottom: 20px">
        <div class="add-comment">
            <h2> Add Comment</h2>
        </div>
        <div class="editContainerForm">
            <form id="comment" name="comment" action="controller" method="post">
                <input type="hidden" name="command" value="confirm-comment">
                <div class="row">
                    <div class="labelCol">
                        <h3 class="label"><fmt:message key="label.name"/></h3>
                    </div>
                    <div class="inputCol">
                        <input type="text" name="commenter-name" value="">
                    </div>
                </div>
                <div class="row">
                    <div class="labelCol">
                        <h3 class="label"><fmt:message key="label.email"/></h3>
                    </div>
                    <div class="inputCol">
                        <input type="text" name="commenter-email" value="">
                    </div>
                </div>
                <div class="row">
                    <div class="labelCol">
                        <h3 class="label"><fmt:message key="label.comment"/></h3>
                    </div>
                    <div class="inputCol">
                        <textarea id="subject" name="subject" placeholder=" <fmt:message key="label.write"/> "
                                  style="height:200px"></textarea>
                    </div>
                </div>
                <div class="row">
                    <input type="submit" value="<fmt:message key="button.submit"/> ">
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/commoncode/footer.jsp" />
</body>
</html>
