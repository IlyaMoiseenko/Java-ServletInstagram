<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%@include file="_header.jsp"%>
<div class="container-fluid bg-3 text-center border-bottom">
    <br>
    <div class="row">
        <div class="col-md d-flex align-items-center justify-content-center">
            <img src="data:image/jpg;base64,${user.getPhoto()}" width="150" height="150" alt="fsd"/>
        </div>
        <div class="col-md text-md-start">
            <h3>${user.getName()}</h3>
            <h4>@${user.getUsername()}</h4>

            <c:if test="${sessionScope.user.username == requestScope.account.username}">
                <a href="/user/account/edit" class="btn btn-sm btn-danger" role="button" aria-pressed="true">Edit</a>
            </c:if>
        </div>
        <div class="col-md">
            <div class="container">
                <div class="row">
                    <a class="col" href="#">${requestScope.followersCnt} Followers</a>
                    <a class="col" href="#">${requestScope.followingCnt} Following</a>
                </div>
                <c:if test="${sessionScope.user.username != requestScope.account.username}">
                    <br>
                    <div class="row">
                        <form action="/#" method="post">
                            <c:choose>
                                <c:when test="${!requestScope.isAlreadyFollowed}">
                                    <button type="button" class="btn btn-sm btn-success">Follow</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-sm btn-danger">Unfollow</button>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                </c:if>
            </div>

        </div>
    </div>
    <br>
</div>
</body>
</html>
