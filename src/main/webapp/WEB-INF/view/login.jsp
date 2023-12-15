<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/static/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/static/css/common.css" rel="stylesheet" type="text/css">
    <title>login</title>
</head>
<body style="margin: 0">
<div class="wrapper">
    <form action="${pageContext.servletContext.contextPath}/auth/signin" method="post" class="form">
        <h1 class="header">Login</h1>
        <div class="element">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="username" required/>
        </div>
        <div class="element">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="password" required/>
        </div>
        <div class="error">${message}</div>
        <button type="submit" class="submit">
            Login
        </button>
        <div class="link">
            <span>Don't have an account?</span>
            <a href="${pageContext.servletContext.contextPath}/auth/signup" class="link">
                Sing Up
            </a>
        </div>
    </form>
</div>
</body>
</html>
