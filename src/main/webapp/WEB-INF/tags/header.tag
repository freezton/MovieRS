<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value='${sessionScope["user"]}' var="user"/>
<%@ attribute name="title" required="true" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/header.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/admin.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/movieDetails.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/movies.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/modal.css">
    <title>${title}</title>
</head>
<header class="header">
    <ul class="header-list">
        <c:choose>
            <c:when test="${user.getRole().toString() == 'ADMIN'}">
                <c:if test="${user.getRole().toString() == 'ADMIN'}">
                    <li class="left-link">
                        <a href="${pageContext.servletContext.contextPath}/users">
                            Admin Panel
                        </a>
                    </li>
                </c:if>
            </c:when>
            <c:otherwise>
                <li class="left-link">
                    <a href="${pageContext.servletContext.contextPath}/movies">
                        Movie Ratings
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
        <ul class="right-header">
            <li class="header-name"><c:out value="${user.getUsername()}"/></li>
            <li class="header-logout">
                <form action="${pageContext.servletContext.contextPath}/auth/logout" method="POST">
                    <button type="submit">
                        Logout
                    </button>
                </form>
            </li>
        </ul>
    </ul>
</header>
<jsp:doBody />
</html>