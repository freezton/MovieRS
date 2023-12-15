<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/registration.css">
    <title>Registration</title>
</head>
<body style="margin: 0">
<div class="wrapper">
    <form action="${pageContext.servletContext.contextPath}/auth/signup" method="post" class="form">
        <h1 class="header">Registration</h1>
        <div class="element">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Username" required/>
        </div>
        <div class="element">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Password" required/>
        </div>
        <div class="element">
            <label for="rePassword">Repeat password:</label>
            <input type="password" id="rePassword" name="rePassword" placeholder="Password" required/>
        </div>
        <div class="error">${message}</div>
        <button type="submit" class="submit">
            Create account
        </button>
        <div class="link">
            <span>Already have an account?</span>
            <a href="${pageContext.servletContext.contextPath}/auth/signin">
                Sign In
            </a>
        </div>
    </form>
</div>
</body>
</html>