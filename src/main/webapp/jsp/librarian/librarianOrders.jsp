<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href=".../../../../css/mainStyle.css">
    <link rel="stylesheet" href=".../../../../css/tableStyle.css">
</head>

<body>
    <%@ include file="../constant/librarianNavigation.html"%>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1>Orders List:</h1>
            <div class="container">
                <div class="tableContainer">
                    <table class="tableList">
                        <tr>
                            <th>
                                <h3> Id </h3>
                            </th>
                            <th>
                                <h3> Book Id</h3>
                            </th>
                            <th>
                                <h3> User Id </h3>
                            </th>
                            <th>
                                <h3> Order Date </h3>
                            </th>
                            <th>
                                <h3> Returning Date </h3>
                            </th>
                            <th>
                                <h3> Reading Place</h3>
                            </th>
                            <th>
                                <h3> Returned </h3>
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
                                <h4> 2</h4>
                            </td>
                            <td>
                                <h4> 4 </h4>
                            </td>
                            <td>
                                <h4> 22-05-2018 </h4>
                            </td>
                            <td>
                                <h4> 27-05-2018 </h4>
                            </td>
                            <td>
                                <h4> HOME</h4>
                            </td>
                            <td>
                                <h4> No </h4>
                            </td>
                            <td> <a class="edit" name="editOrder" href="librarianEditOrder.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 2 </h4>
                            </td>
                            <td>
                                <h4> 1</h4>
                            </td>
                            <td>
                                <h4> 3 </h4>
                            </td>
                            <td>
                                <h4> 02-07-2018 </h4>
                            </td>
                            <td>
                                <h4> 17-07-2018 </h4>
                            </td>
                            <td>
                                <h4> HOME</h4>
                            </td>
                            <td>
                                <h4> No </h4>
                            </td>
                            <td> <a class="edit" name="editOrder" href="librarianEditOrder.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 3 </h4>
                            </td>
                            <td>
                                <h4> 4</h4>
                            </td>
                            <td>
                                <h4> 2 </h4>
                            </td>
                            <td>
                                <h4> 12-08-2018 </h4>
                            </td>
                            <td>
                                <h4> 12-08-2018 </h4>
                            </td>
                            <td>
                                <h4> HALL</h4>
                            </td>
                            <td>
                                <h4> No </h4>
                            </td>
                            <td> <a class="edit" name="editOrder" href="librarianEditOrder.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 4 </h4>
                            </td>
                            <td>
                                <h4> 6</h4>
                            </td>
                            <td>
                                <h4> 3 </h4>
                            </td>
                            <td>
                                <h4> 24-09-2018 </h4>
                            </td>
                            <td>
                                <h4> 27-09-2018 </h4>
                            </td>
                            <td>
                                <h4> HOME</h4>
                            </td>
                            <td>
                                <h4> No </h4>
                            </td>
                            <td> <a class="edit" name="editOrder" href="librarianEditOrder.jsp">Edit</a></td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>

    </div>
    <%@ include file="../constant/readerFooter.html"%>
</body>

</html>
