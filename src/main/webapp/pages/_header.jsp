<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../static/main.css">
    <style><%@include file="/static/main.css"%></style>

</head>
<body>
<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <a href="/" class="navbar-brand">Instagram</a>

        <div role="search">
            <c:if test="${user == null}">
                <a class="btn btn-success" href="/register">Register</a>
                <a class="btn btn-success" href="/login">Login</a>
            </c:if>

            <c:if test="${user != null}">
                <a class="btn btn-success" href="/profile">Profile</a>
                <a class="btn btn-success" href="/create-post">Create post</a>
                <a class="btn btn-danger" href="/logout">Logout</a>
            </c:if>
        </div>
    </div>
</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>
