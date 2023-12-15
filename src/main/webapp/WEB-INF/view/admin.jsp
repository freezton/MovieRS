<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value='${sessionScope["user"]}' var="user"/>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:header title="Admin Panel"/>
<body>
<div class="wrapper">
    <%--    <%@ include file="/header.jsp" %>--%>
    <main class="content">
        <a class="back" href="${pageContext.servletContext.contextPath}/movies"><</a>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Rating</th>
                <th>Ban/Unban</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="userInfo" items="${users}">
                <tr>
                    <td>${userInfo.getUsername()}</td>
                    <td>
                        <div class="status-wrapper">
                            <form action="${pageContext.servletContext.contextPath}/users/${userInfo.getId()}" method="POST">
                                <input type="hidden" name="id" value="${userInfo.getId()}">
                                <input type="hidden" name="status" value="${userInfo.getStatus() - 1}">
                                <input type="hidden" name="isBanned" value="${userInfo.getIsBanned()}">
                                <button class="status-operation" type="submit">-</button>
                            </form>
                                ${userInfo.getStatus()}
                            <form action="${pageContext.servletContext.contextPath}/users/${userInfo.getId()}" method="POST">
                                <input type="hidden" name="id" value="${userInfo.getId()}">
                                <input type="hidden" name="status" value="${userInfo.getStatus() + 1}">
                                <input type="hidden" name="isBanned" value="${userInfo.getIsBanned()}">
                                <button class="status-operation" type="submit">+</button>
                            </form>
                        </div>
                    </td>
                    <td style="width: 10%; text-align: center">
                        <form action="${pageContext.servletContext.contextPath}/users/${userInfo.getId()}" method="POST">
                            <input type="hidden" name="id" value="${userInfo.getId()}">
                            <input type="hidden" name="status" value="${userInfo.getStatus()}">
                            <input type="hidden" name="isBanned" value="${!userInfo.getIsBanned()}">
                            <button class="ban" type="submit">${userInfo.getIsBanned() ? "Unban" : "Ban"}</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
</div>
</body>