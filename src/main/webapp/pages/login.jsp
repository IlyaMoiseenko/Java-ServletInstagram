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
<div class="row justify-content-center">
    <div class="col-4">
        <div class="container">
            <c:if test="${loginMessage != null}">
                <div class="alert alert-danger" role="alert">
                        ${loginMessage}
                </div>
            </c:if>

            <form action="/login" method="post">
                <p class="fs-6">    </p>

                <div class="mb-3">
                    <label for="user" class="form-label">Username</label>
                    <input name="username" type="text" class="form-control" id="user" required pattern="\w*">
                    <c:if test="${usernameUsed != null}">
                        <div class="alert alert-danger" role="alert">
                                ${usernameUsed}
                        </div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control" id="exampleInputPassword1">
                </div>

                <div class="d-grid gap-2 col-6 mx-auto">
                    <button class="btn btn-primary" type="submit">Log In</button>
                </div>

                <p class="fs-6">    </p>

                <p class="text-body-secondary">
                    Don`t have an account? <a href="/register">Sign up</a>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
