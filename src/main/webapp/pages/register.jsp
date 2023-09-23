<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form method="post" action="/register" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="exampleInputPass" class="form-label">Your photo:</label>
            <input name="photo" type="file" class="form-control" id="exampleInputPass">
        </div>

        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Your name:</label>
            <input name="name" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>

        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Your surname:</label>
            <input name="surname" type="text" class="form-control" id="exampleInputPassword1">
        </div>

        <div class="mb-3">
            <label for="exampleInputPasswor" class="form-label">Your username:</label>
            <input name="username" type="text" class="form-control" id="exampleInputPasswor">
        </div>

        <div class="mb-3">
            <label for="exampleInputPassword" class="form-label">Your email:</label>
            <input name="email" type="email" class="form-control" id="exampleInputPassword">
        </div>

        <div class="mb-3">
            <label for="exampleInputPasswo" class="form-label">Your password:</label>
            <input name="password" type="password" class="form-control" id="exampleInputPasswo">
        </div>

        <select name="country" class="form-select" aria-label="Default select example">
            <option selected>Choose country</option>

            <c:forEach items="${countries}" var="item">
                <option value="${item.getId()}">${item.getName()}</option>
            </c:forEach>
        </select>

        <select name="city" class="form-select" aria-label="Default select example">
            <option selected>Choose city</option>

            <c:forEach items="${cities}" var="item">
                <option value="${item.getId()}">${item.getName()}</option>
            </c:forEach>
        </select>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
