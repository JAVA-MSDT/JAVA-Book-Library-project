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
            <h1>Readers List:</h1>
             <div class="btnContainer">
                 <a href="librarianEditReader.jsp" name="librarianAddReader"> Add Reader </a>
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
                                <h3> Last Name </h3>
                            </th>
                            <th>
                                <h3> E-mail </h3>
                            </th>
                            <th>
                                <h3> Login </h3>
                            </th>
                            <th>
                                <h3> Password</h3>
                            </th>
                            <th>
                                <h3> Blocked </h3>
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
                                <h4> Ahmed</h4>
                            </td>
                            <td>
                                <h4> Samy </h4>
                            </td>
                            <td>
                                <h4> serenitydiver@hotmail.com </h4>
                            </td>
                            <td>
                                <h4> Login </h4>
                            </td>
                            <td>
                                <h4> Password</h4>
                            </td>
                            <td>
                                <h4> No </h4>
                            </td>
                            <td> <a class="edit" name="librarianEditReader" href="librarianEditReader.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 2 </h4>
                            </td>
                            <td>
                                <h4> Sacha</h4>
                            </td>
                            <td>
                                <h4> Alexander </h4>
                            </td>
                            <td>
                                <h4> librarian@epam.com </h4>
                            </td>
                            <td>
                                <h4> Login </h4>
                            </td>
                            <td>
                                <h4> Password</h4>
                            </td>
                            <td>
                                <h4> No </h4>
                            </td>
                             <td> <a class="edit" name="librarianEditReader" href="librarianEditReader.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 3 </h4>
                            </td>
                            <td>
                                <h4> reader</h4>
                            </td>
                            <td>
                                <h4> read </h4>
                            </td>
                            <td>
                                <h4> reader@epam.com </h4>
                            </td>
                            <td>
                                <h4> Login </h4>
                            </td>
                            <td>
                                <h4> Password</h4>
                            </td>
                            <td>
                                <h4> No </h4>
                            </td>
                             <td> <a class="edit" name="librarianEditReader" href="librarianEditReader.jsp">Edit</a></td>
                        </tr>
                        <tr>
                            <td>
                                <h4> 4 </h4>
                            </td>
                            <td>
                                <h4> Reading</h4>
                            </td>
                            <td>
                                <h4> Book </h4>
                            </td>
                            <td>
                                <h4> book@epam.com </h4>
                            </td>
                            <td>
                                <h4> Login </h4>
                            </td>
                            <td>
                                <h4> Password</h4>
                            </td>
                            <td>
                                <h4> No </h4>
                            </td>
                             <td> <a class="edit" name="librarianEditReader" href="librarianEditReader.jsp">Edit</a></td>
                        </tr>


                    </table>
                </div>
            </div>

        </div>

    </div>
    <%@ include file="../constant/readerFooter.html"%>
</body>

</html>
