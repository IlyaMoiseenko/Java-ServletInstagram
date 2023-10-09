<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/main.css">

</head>
<body>
    <%@include file="_header.jsp"%>


    <div class="container">
        <div class="row justify-content-center">
            <div class="col-5 mt-3 justify-content-center">
                <c:if test="${user != null}">
                    <h1>All posts by following</h1>
                    <ul>
                        <c:forEach items="${posts}" var="item">
                            <div class="card mt-5" style="width: 30rem;">
                                <p style="margin-left: 20px">
                                    <a href="/user/profile?username=${item.getUser().getUsername()}"
                                       class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">${item.getUser().getUsername()}
                                    </a>
                                </p>

                                <a href="/user/viewpost?id=${item.getId()}">
                                    <img src="data:image/jpg;base64,${item.getPhoto()}" width="100%"/>
                                </a>

                                <div class="card-body">
                                    <p class="card-text">${item.getDescription()}</p>
                                </div>
                            </div>
                        </c:forEach>

                        <h1>All user posts</h1>
                        <ul>
                            <c:forEach items="${allPosts}" var="item">
                                <div class="card mt-5" style="width: 30rem;">
                                    <p style="margin-left: 20px">
                                        <a href="/user/profile?username=${item.getUser().getUsername()}"
                                           class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">${item.getUser().getUsername()}
                                        </a>
                                    </p>

                                    <a href="/user/viewpost?id=${item.getId()}">
                                        <img src="data:image/jpg;base64,${item.getPhoto()}" width="100%"/>
                                    </a>

                                    <div class="card-body">
                                        <p class="card-text">${item.getDescription()}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </ul>
                    </ul>
                </c:if>

                <c:if test="${user == null}">
                    <h1>All user posts</h1>
                    <ul>
                        <c:forEach items="${posts}" var="item">
                            <div class="card mt-5" style="width: 30rem;">
                                <p style="margin-left: 20px">
                                    <a href="/user/profile?username=${item.getUser().getUsername()}"
                                       class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">${item.getUser().getUsername()}
                                    </a>
                                </p>

                                <a href="/user/viewpost?id=${item.getId()}">
                                    <img src="data:image/jpg;base64,${item.getPhoto()}" width="100%"/>
                                </a>

                                <div class="card-body">
                                    <p class="card-text">${item.getDescription()}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
