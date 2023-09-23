<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create post</title>
</head>
<body>
<%@include file="_header.jsp"%>

<div class="container">
    <h1 class="text-align-center">Create a new post</h1>

    <form method="post" action="/create-post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="exampleInputPass" class="form-label">Post photo:</label>
            <input name="photo" type="file" class="form-control" id="exampleInputPass">
        </div>

        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Description:</label>
            <input name="description" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>

        <button type="submit" class="btn btn-success">Create post</button>
    </form>
</div>
</body>
</html>
