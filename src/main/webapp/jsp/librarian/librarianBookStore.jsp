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
    <%@ include file="../constant/librarianNavigation.jsp"%>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1>Book Store:</h1>
            <div class="btnContainer">
                 <a href="librarianEditBook.jsp" name="librarianAddReader"> Add Book </a>
            </div>
            <div class="container">
                <div class="tableContainer">
                    <table class="tableList">
                        <tr>
                            <th>
                                <h3> Id </h3>
                            </th>
                            <th>
                                <h3> Name</h3>
                            </th>
                            <th>
                                <h3> Quantity </h3>
                            </th>
                            <th>
                                <h3> Edit </h3>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <h4> 1 </h4>
                            </td>
                            <td>
                                <h4> CS50</h4>
                            </td>
                            <td> 20 </td>
                            <td> <a class="edit" name="librarianEditBook" href="librarianEditBook.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 2 </h4>
                            </td>
                            <td>
                                <h4> JAVA SERVLET</h4>
                            </td>
                            <td> 14 </td>
                            <td> <a class="edit" name="librarianEditBook" href="librarianEditBook.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 3 </h4>
                            </td>
                            <td>
                                <h4> JAVA EE</h4>
                            </td>
                            <td> 8 </td>
                            <td> <a class="edit" name="librarianEditBook" href="librarianEditBook.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 4 </h4>
                            </td>
                            <td>
                                <h4> JAVA SE</h4>
                            </td>
                            <td> 35 </td>
                            <td> <a class="edit" name="librarianEditBook" href="librarianEditBook.jsp">Edit</a></td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>

    </div>
    <%@ include file="../constant/readerFooter.jsp"%>
</body>

</html>