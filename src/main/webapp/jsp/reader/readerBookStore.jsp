<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href=".../../../../css/tableStyle.css">
    <link rel="stylesheet" href=".../../../../css/mainStyle.css">
</head>

<body>
    <%@ include file="../constant/readerNavigation.html"%>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1>Book Store:</h1>
            <div class="container">
                <div class="tableContainer">
                    <table class="tableList">
                        <tr>
                            <th>
                                <h2> Id </h2>
                            </th>
                            <th>
                                <h2> Name</h2>
                            </th>
                            <th>
                                <h2> Order </h2>
                            </th>
                            <th>
                                <h2> Cancel </h2>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <h3> 1 </h3>
                            </td>
                            <td>
                                <h3> CS50</h3>
                            </td>
                            <td> <a class="edit" name="orderBook" href="#">Order</a></td>
                            <td> <a class="cancel" name="cancelBook" href="#">Cancel</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h3> 2 </h3>
                            </td>
                            <td>
                                <h3> JAVA SERVLET</h3>
                            </td>
                            <td> <a class="edit" name="orderBook" href="#">Order</a></td>
                            <td> <a class="cancel" name="cancelBook" href="#">Cancel</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h3> 3 </h3>
                            </td>
                            <td>
                                <h3> JAVA EE</h3>
                            </td>
                            <td> <a class="edit" name="orderBook" href="#">Order</a></td>
                            <td> <a class="cancel" name="cancelBook" href="#">Cancel</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h3> 4 </h3>
                            </td>
                            <td>
                                <h3> JAVA SE</h3>
                            </td>
                            <td> <a class="edit" name="orderBook" href="#">Order</a></td>
                            <td> <a class="cancel" name="cancelBook" href="#">Cancel</a></td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>

    </div>
    <%@ include file="../constant/readerFooter.jsp"%>
</body>

</html>
