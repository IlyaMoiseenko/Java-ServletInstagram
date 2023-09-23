<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>

</head>
<body>
<%@include file="_header.jsp"%>

<div class="row justify-content-center">
    <div class="col-4">
        <form class="mt-3 needs-validation" action="/register" method="post" enctype="multipart/form-data">
            <div class="row mb-3">
                <div class="col">
                    <label for="photo" class="form-label">Photo</label>
                    <input name="photo" type="file" class="form-control" id="photo">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <label for="name" class="form-label">First name</label>
                    <input name="name" type="text" class="form-control" id="name" required pattern="(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <label for="surname" class="form-label">Second name</label>
                    <input name="surname" type="text" class="form-control" id="surname" required pattern="(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <label for="username" class="form-label">Username</label>
                    <input name="username" type="text" class="form-control" id="username" required pattern="(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})">
                </div>
            </div>

            <div class="mb-3">
                <label for="Email" class="form-label">Email address</label>
                <input name="email" type="email" class="form-control" id="Email" required pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
                <c:if test="${emailUsed != null}">
                    <div class="alert alert-danger" role="alert">
                            ${emailUsed}
                    </div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="Password" class="form-label">Password</label>
                <input name="password" type="password" class="form-control" id="Password" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$">
            </div>

            <div class="mb-3">
                <select name="country" class="form-select" aria-label="Default select example">
                    <option selected>Choose country</option>

                    <c:forEach items="${countries}" var="item">
                        <option value="${item.getId()}">${item.getName()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <select name="city" class="form-select" aria-label="Default select example">
                    <option selected>Choose city</option>

                    <c:forEach items="${cities}" var="item">
                        <option value="${item.getId()}">${item.getName()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-primary" type="submit">Sign In</button>
            </div>

            <p class="fs-6">    </p>

            <p class="text-body-secondary">
                Already have an account? <a href="/login">Log in</a>
            </p>

        </form>
    </div>
</div>

</body>
</html>
