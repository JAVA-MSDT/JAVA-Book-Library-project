<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href=".../../../../css/mainStyle.css">

</head>

<body>
    <%@ include file="../constant/librarianNavigation.html"%>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1>Profile Page:</h1>
            <table class="readerInfo">
                <tr>
                    <td>
                        <h2>Name: </h2>
                    </td>
                    <td>
                        <h3>Librarian Profile</h3>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2>E-mail: </h2>
                    </td>
                    <td>
                        <h3> librarian@epamLibrary.com</h3>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2>Login: </h2>
                    </td>
                    <td>
                        <h3> LoginName</h3>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2>Password: </h2>
                    </td>
                    <td>
                        <h3> password</h3>
                    </td>
                </tr>
            </table>
            <p class="editInfo"><a href="#">Edit Info</a></p>
        </div>

    </div>

    <%@ include file="../constant/readerFooter.html"%>
</body>

</html>
