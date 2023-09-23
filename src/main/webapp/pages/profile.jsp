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
            <img src="data:image/jpg;base64,${viewedUser.getPhoto()}" width="150" height="150" alt="fsd"/>
        </div>
        <div class="col-md text-md-start">
            <h3>${viewedUser.getName()} ${viewedUser.getSurname()}</h3>
            <h4>@${viewedUser.getUsername()}</h4>

            <c:if test="${viewedUser.getUsername() == user.getUsername()}">
                <a href="/user/account/edit" class="btn btn-sm btn-danger" role="button" aria-pressed="true">Edit</a>
            </c:if>
        </div>
        <div class="col-md">
            <div class="container">
                <div class="row">
                    <a class="col" href="#">${requestScope.followersCnt} Followers</a>
                    <a class="col" href="#">${requestScope.followingCnt} Following</a>
                </div>
                <c:if test="${viewedUser.getUsername() != user.getUsername()}">
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

<div class="border-bottom">
    <div class="container-fluid text-center">
        <div class="row">
            <c:forEach items="${userPosts}" var="item">
                <div class="col-sm-4">
                    <div class="card" style="width: 100%">
                        <a href="#">
                            <img class="card-img-top" src="data:image/jpg;base64,${item.getPhoto()}" style="width:100%; height: auto;  aspect-ratio: 16/9" alt="Image"/>
                        </a>

                        <div class="card-body">
                            <p class="card-text">${item.getDescription()}</p>
                        </div>

                        <br>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
</body>
</html>
