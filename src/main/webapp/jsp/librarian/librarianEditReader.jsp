<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <title>Epam Library</title>
    <meta charset="utf-8">
    <meta name="author" content="Ahmed Samy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href=".../../../../css/editForm.css">
    <link rel="stylesheet" href=".../../../../css/mainStyle.css">
</head>

<body>
    <%@ include file="../constant/librarianNavigation.html"%>
    <div class="profileContainer">
        <div class="basicInfo">
            <h1>Edit Reader:</h1>
            <div class="container">
                <div class="editContainerForm">
                    <form id="librarianReaderForm" name="librarianEditReader">
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label">Reader Id:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReaderId">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Name:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReaderName">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Last Name:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReaderLastName">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label">E-mail:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReaderEmail">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Login:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReaderLogin">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Password:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReaderPassword">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Blocked:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReaderBlocked">
                            </div>
                        </div>
                        <div class="row">
                            <input type="submit" value="Save Update">
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
    <%@ include file="../constant/readerFooter.html"%>
</body>

</html>
