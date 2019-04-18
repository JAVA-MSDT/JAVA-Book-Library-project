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
            <h1>Edit Order:</h1>
            <div class="container">
                <div class="editContainerForm">
                    <form id="librarianOrderForm" name="librarianEditOrder">
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label">Order Id:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianOrderId">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Book Id:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianBookIdOrder">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Reader Id:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReaderIdOrder">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label">Order Date:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianOrderDate">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Returning Date:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReturningDate">
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Reading Place:</h3>
                            </div>
                            <div class="inputCol">
                                <select id="librarianReadingPlace" name="librarianReadingPlace">
                                <option value="HOME">Home</option>
                                <option value="HALL">Hall</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="labelCol">
                                <h3 class="label"> Returned:</h3>
                            </div>
                            <div class="inputCol">
                                <input type="text" name="librarianReturned">
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
