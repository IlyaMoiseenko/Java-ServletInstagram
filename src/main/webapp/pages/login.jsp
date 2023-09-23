<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="_header.jsp"%>
    <div class="container">
        <form method="post" action="/login">
            <div class="mb-3">
                <label for="exampleInputPass" class="form-label">Your username:</label>
                <input name="username" type="text" class="form-control" id="exampleInputPass">
            </div>

            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Your password:</label>
                <input name="password" type="password" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            </div>

            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
</body>
</html>
