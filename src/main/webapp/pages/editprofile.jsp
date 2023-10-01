<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Edit profile</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
  <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--%>
</head>
<body>
<jsp:include page="_header.jsp"/>

<div class="container h-100 mt-3">
  <div class="row h-100 justify-content-center align-items-center">
    <div class="card">
      <div class="d-flex justify-content-end">
        <c:url value="" var="userProfileURL">
          <c:param name="username" value="${user.getUsername()}"/>
        </c:url>
        <a href='<c:out value="${userProfileURL}"/>'>[x]close</a>
      </div>
      <br>
      <div class="text-center">
        <c:choose>
          <c:when test="${user.getPhoto() != null}">
            <img class="rounded-circle border border-dark" width="150" height="150" src="data:image/jpg;base64,${user.getPhoto()}">
          </c:when>
          <c:otherwise>
            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/2048px-User-avatar.svg.png"
                 class="rounded-circle border border-dark" width="150" height="150">
          </c:otherwise>
        </c:choose>
        <h2 class="card-text">@${user.getUsername()}</h2>
        <p class="card-text">${user.getName()}</p>
        <p class="card-text">${user.getEmail()}</p>
      </div>
      <div class="card-body">
        <form method="post" enctype="multipart/form-data">
          <label for="newName" class="form-label">New name:</label>
          <input name="newName" type="text" class="form-control" id="newName" value="${user.getName()}"><br>

          <label for="newEmail" class="form-label">New e-mail:</label>
          <input name="newEmail" type="text" class="form-control" id="newEmail" value="${user.getEmail()}"><br>

          <label for="newImage" class="form-label">New profile's image:</label>
          <input name="newImage" type="file" class="form-control" id="newImage"><br>

          <p>Change password:</p>
          <label for="newPassword" class="form-label">New password:</label>
          <input name="newPassword" type="password" class="form-control" id="newPassword"><br>

          <c:if test="${requestScope.errornessage != null}">
            <p style="color: red">${requestScope.errornessage}</p><br>
          </c:if>

          <button type="submit" formaction="/edit-profile" class="btn btn-primary">Edit</button>
          <button type="submit" formaction="/user/account/delete" class="btn btn-danger">Delete</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>