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
            <h1>Edit Book:</h1>
            <div class="container">
                            <div class="editContainerForm">
                                <form id="librarianBookForm" name="librarianEditBook">
                                    <div class="row">
                                        <div class="labelCol">
                                            <h3 class="label">Book Id:</h3>
                                        </div>
                                        <div class="inputCol">
                                            <input type="text" name="librarianBookId">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="labelCol">
                                            <h3 class="label">Book Name:</h3>
                                        </div>
                                        <div class="inputCol">
                                            <input type="text" name="librarianBookName">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="labelCol">
                                            <h3 class="label">Book Quantity:</h3>
                                        </div>
                                        <div class="inputCol">
                                            <input type="text" name="librarianBookQuantity">
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